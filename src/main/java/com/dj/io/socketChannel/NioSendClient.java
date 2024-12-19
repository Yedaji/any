package com.dj.io.socketChannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 使用SocketChannel发送文件的实战案例
 *
 * @author ydj
 * @date 2024/11/16 17:37
 **/
public class NioSendClient {
    private static Charset charset = StandardCharsets.UTF_8;

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(1 << 30);
        System.out.println(1 << 20);
        System.out.println(1 << 10);
        NioSendClient nioSendClient = new NioSendClient();
        nioSendClient.sendFile();
    }
    /**
     * 向服务端传输文件
     */
    public void sendFile() {
        try {
            String srcPath = "/Users/ydj/code/DataStructures/src/main/java/com/dj/io/socketChannel/字节大佬总结的Java面试资料.pdf";
            String destFile = "af.pdf";
            File file = new File(srcPath);
            if (!file.exists()) {
                System.out.println("文件不存在");
                return;
            }
            FileChannel fileChannel = new FileInputStream(file).getChannel();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().connect(new InetSocketAddress("127.0.0.1", 9999));
            socketChannel.configureBlocking(false);
            System.out.println("Client成功连接服务端");
            while (!socketChannel.finishConnect()) {
                // 不停地自旋，等待，或者做其他事情
            }
            // 发送文件名称和长度
            ByteBuffer buffer = sendFIleNameAndLength(destFile, file, socketChannel);

            // 发送文件内容
            int length = sendContent(file, fileChannel, socketChannel, buffer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送文件内容
     */
    private int sendContent(File file, FileChannel fileChannel, SocketChannel socketChannel, ByteBuffer buffer) throws IOException {
        // 发送文件内容
        System.out.println("开始传输文件");
        int length = 0;
        long progress = 0; // 进度
        //  从fileChannel中读取数据写入到buffer中
        while ((length = fileChannel.read(buffer)) > 0) {
            // 写模式变为读模式
            buffer.flip();
            // 发送buffer到socket通道
            socketChannel.write(buffer);
            // buffer变为写模式
            buffer.clear();
            progress += length;
            System.out.println("| " + (100 * progress / file.length()) + "% |");
        }
        return length;
    }

    // 发送文件名称和长度
    private ByteBuffer sendFIleNameAndLength(String destFile, File file, SocketChannel socketChannel) throws IOException {

        ByteBuffer fileNameByteBuffer = charset.encode(destFile);
        // 获取一个1024字节的字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 发送文件名称长度
        int fileNameLen = fileNameByteBuffer.capacity();
        // 缓冲区写入文件名长度
        buffer.putInt(fileNameLen);
        // 翻转缓冲区，从写缓冲区变为读缓冲区
        buffer.flip();
        // socket通道写入缓冲区数据，发送文件名称长度
        socketChannel.write(buffer);
        // 翻转缓冲区，从读缓冲区变为写缓冲区
        buffer.clear();
        System.out.println("Client文件名称长度发送完成：" + fileNameLen);
        // 发送文件名称
        socketChannel.write(fileNameByteBuffer);
        System.out.println("Client文件名称发送完成: " + destFile);
        // 发送文件长度
        buffer.putLong(file.length());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        System.out.println("Client文件长度发送完成：" + file.length());
        return buffer;
    }
}
