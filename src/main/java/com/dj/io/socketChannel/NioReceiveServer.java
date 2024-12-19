package com.dj.io.socketChannel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * socketChannel服务端接收文件
 *
 * @author ydj
 * @date 2024/11/17 10:29
 **/
public class NioReceiveServer {
    private static final String RECEIVE_PATH = "/Users/ydj/code/DataStructures/src/main/java/com/dj/io/socketChannel";
    // 使用map保存每个客户端传输
    // 当OP_READ通道可读时，根据channel找到对应的对象
    Map<SelectableChannel, Client> clientMap = new HashMap<SelectableChannel, Client>();
    private Charset charset = StandardCharsets.UTF_8;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        NioReceiveServer nioReceiveServer = new NioReceiveServer();
        nioReceiveServer.startServer();
    }

    private void startServer() throws IOException {
        // 1.获得选择器
        Selector selector = Selector.open();
        // 2.获得通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 3.设置非阻塞模式
        serverChannel.configureBlocking(false);
        // 4.绑定连接
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        serverChannel.bind(address);
        // 5.将通道注册到选择器上，并注册IO事件为"接收新连接"
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("serverChannel is listening...");
        // 6.处理selectorKey
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 7.若是新连接事件
                if (selectionKey.isAcceptable()) {
                    // 8.获取客户端新连接
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = server.accept();
                    if (socketChannel == null) continue;
                    // 9.客户端新连接，切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 10. 将客户端新连接通道注册到Selector上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    // 余下为业务逻辑
                    Client client = new Client();
                    client.remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                    clientMap.put(socketChannel, client);
                    System.out.println(socketChannel.getRemoteAddress() + "连接成功...");
                } else if (selectionKey.isReadable()) {
                    // 处理数据
                    processData(selectionKey);
                }
                // 移除事件
                iterator.remove();
            }
        }
    }

    /**
     * 处理客户端传输过来的数据
     *
     * @param selectionKey
     */
    private void processData(SelectionKey selectionKey) throws IOException {
        Client client = clientMap.get(selectionKey.channel());
        // 获得客户端通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        int num = 0;
        try {
            // 切换为写模式
            buffer.clear();
            while ((num = socketChannel.read(buffer)) > 0) {
                // 缓冲区切换为读模式
                buffer.flip();
                // 客户端发送过来的，首先处理文件名 根据客户端发送顺序处理
                if (null == client.fileName) {
                    if (buffer.capacity() < 4) {
                        continue;
                    }
                    int fileNameLen = buffer.getInt();
                    byte[] fileNameBytes = new byte[fileNameLen];
                    buffer.get(fileNameBytes);
                    // 文件名
                    String fileName = new String(fileNameBytes, charset);
                    File directory = new File(RECEIVE_PATH);
                    if (!directory.exists()) {
                        directory.mkdir();
                    }
                    System.out.println("NIO 传输目标dir：" + directory);
                    client.fileName = fileName;
                    String fullName = directory.getAbsolutePath() + File.separatorChar + fileName;
                    System.out.println("NIO 传输目标文件：" + fullName);
                    File file = new File(fileName);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    FileChannel fileChannel = fileOutputStream.getChannel();
                    client.outChannel = fileChannel;
                    if (buffer.capacity() < 0) {
                        continue;
                    }
                    // 文件长度
                    long fileLength = buffer.getLong();
                    client.fileLength = fileLength;
                    long startTime = System.currentTimeMillis();
                    client.startTime = startTime;
                    System.out.println("NIO 传输开始：");
                    client.receiveLength += buffer.capacity();
                    if (buffer.capacity() > 0) {
                        // 写入文件
                        client.outChannel.write(buffer);
                    }
                    if (client.isFinished()) {
                        finished(selectionKey, client);
                    }
                    buffer.clear();
                } else {
                    // 客户端发送过来的，最后是文件内容
                    client.receiveLength += buffer.capacity();
                    // 写入文件
                    client.outChannel.write(buffer);
                    if (client.isFinished()) {
                        finished(selectionKey, client);
                    }
                    buffer.clear();
                }
            }
            selectionKey.channel();
        } catch (IOException e) {
            selectionKey.channel();
            e.printStackTrace();
            return;
        }
        // 调用close为-1，到达末尾
        if (num == -1) {
            finished(selectionKey, client);
            buffer.clear();
        }
    }

    private void finished(SelectionKey selectionKey, Client client) throws IOException {
        client.outChannel.close();
        System.out.println("上传完毕");
        selectionKey.cancel();
        System.out.println("文件接收成功，File Name: " + client.fileName);
        System.out.println("Size：" + client.fileLength);
        System.out.println("NIO IO 传输毫秒数：" + (System.currentTimeMillis() - client.startTime));
    }

    /**
     * 服务端保存的客户对象，对应一个客户端文件
     */
    static class Client {
        // 文件名
        String fileName;
        // 长度
        long fileLength;
        // 开始传输时间；
        long startTime;
        // 客户端的地址
        InetSocketAddress remoteAddress;
        // 输出的文件通道
        FileChannel outChannel;
        // 接收长度
        long receiveLength;

        public boolean isFinished() {
            return receiveLength >= fileLength;
        }
    }

}
