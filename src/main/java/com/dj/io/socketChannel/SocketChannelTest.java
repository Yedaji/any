package com.dj.io.socketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * NIO
 *
 * @author ydj
 * @date 2024/11/16 17:31
 **/
public class SocketChannelTest {
    public static void main(String[] args) throws IOException {
        getOpen();
    }

    private static void getOpen() throws IOException {
        // 获得一个套接字传输通道
        SocketChannel open = SocketChannel.open();
        // 设置为非阻塞模式
        open.configureBlocking(false);
       while (! open.connect(new InetSocketAddress("127.0.0.1", 1990))){

       }
    }
}
