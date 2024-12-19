package com.dj.io.discard;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * discard服务器；
 * 仅读取客户端通道的输入数据，读取完成后字节关闭客户端通道，并且直接抛弃掉（discard)读取到的数据
 *
 * @author ydj
 * @date 2024/11/17 10:04
 **/
public class NioDiscardServer {
    public static void main(String[] args) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        System.out.println(simpleDateFormat.format(new Date()));
        startServer();
    }

    public static void startServer() throws IOException {
        // 1.获取选择器
        Selector selector = Selector.open();
        // 2.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 3.设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 4.绑定连接
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8888));
        System.out.println("服务器启动成功");
        // 5.将通道注册的"接收新连接"IO事件注册到选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6.轮训关注的事件
        while (selector.select() > 0) {
            // 7.获得选择器集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                // 8.获得单个选择器，并处理
                SelectionKey selectionKey = iterator.next();
                // 9.筛选key类型
                if (selectionKey.isAcceptable()) {
                    // 10."连接就绪"IO事件，就获取客户端连接通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 11.将新连接切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 12.将新连接的通道的可读事件注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 13.若是"可读"IO事件，则读取数据
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 14.读取数据，然后丢弃
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, buffer.limit()));
                    }
                    socketChannel.close();
                }
                // 15.移除selectionKey
                iterator.remove();
            }
        }
        // 16.关闭连接
        serverSocketChannel.close();
    }
}
