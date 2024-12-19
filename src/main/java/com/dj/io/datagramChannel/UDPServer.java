package com.dj.io.datagramChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * UDP服务端
 *
 * @author ydj
 * @date 2024/11/16 18:31
 **/
public class UDPServer {
    public static void main(String[] args) throws IOException {
        new UDPServer().receive();
    }
    public void receive() throws IOException {
        // 获取DatagramChannel
        DatagramChannel datagramChannel = DatagramChannel.open();
        // 设置为阻塞模式
        datagramChannel.configureBlocking(false);
        // 绑定监听地址
        datagramChannel.bind(new InetSocketAddress("127.0.0.1", 19990));
        System.out.println("UDP服务器启动成功！");
        // 开启一个通道选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器
        datagramChannel.register(selector, SelectionKey.OP_READ);
        // 通过选择器查询IO事件
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 循环IO事件
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 可读事件，有数据到来
                if (selectionKey.isReadable()) {
                    // 读取DatagramChannel数据
                    SocketAddress client = datagramChannel.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, buffer.limit()));
                    buffer.clear();
                }
            }
            iterator.remove();
        }
        // 关闭选择器和通道
        selector.close();
        datagramChannel.close();
    }
}
