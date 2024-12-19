package com.dj.io.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * reactor
 *
 * @author ydj
 * @date 2024/11/17 17:54
 **/
public class ReactorServer implements Runnable {

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public ReactorServer() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8899));
        AcceptorHandler acceptorHandler = new AcceptorHandler();
        SelectionKey key = serverSocketChannel.register(selector, 0, acceptorHandler);
//            key.attach(acceptorHandler);
        key.interestOps(SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws IOException {
        new Thread(new ReactorServer()).start();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        disPath(key);
                    }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void disPath(SelectionKey key) {
        Runnable runnable = (Runnable) key.attachment();
        if (null != runnable) {
            runnable.run();
        }
    }
    public class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            try {
                // 获得一个客户端连接
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (null != socketChannel) {new SendAndPrintHandler(selector, socketChannel);}
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
