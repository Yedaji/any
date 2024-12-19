package com.dj.io.reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 新连接处理器
 *
 * @author ydj
 * @date 2024/11/17 17:36
 **/
public class AcceptorHandler implements Runnable {
    ServerSocketChannel serverSocketChannel;
    Selector selector;

    public AcceptorHandler(ServerSocketChannel serverSocketChannel, Selector selector) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void run() {
        try {
            // 获得一个客户端连接
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            new SendAndPrintHandler(selector, socketChannel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
