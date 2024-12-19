package com.dj.io.reactor.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * selector channel key buffer
 *
 * 读取客户端的输入并回显到客户端
 * EchoServerReactor 反应器类
 * AcceptorHandler 新连接处理器
 * EchHandler 回显处理器
 *
 * @author ydj
 * @date 2024/11/17 14:02
 **/

//反应器类
public class EchoServerReactor implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocketChannel;
    // 构造器

    public EchoServerReactor() throws IOException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);
        // 打开连接器 监听通道
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(address);
        SelectionKey key = serverSocketChannel.register(selector, 0, new AcceptorHandler());

        System.out.println("服务端已经开始监听：" + address);
        key.interestOps(SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    // 反应器负责dispatch收到的事件
                    SelectionKey key = iterator.next();
                    dispatch(key);
                }
                selectionKeys.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分发io事件
     *
     * @param key
     */
    private void dispatch(SelectionKey key) {
        Runnable handler = (Runnable) key.attachment();
        // 调用之前，附加绑定到选择键的handler对象
        if (handler != null) {
            handler.run();
        }
    }

    /**
     * 新连接处理器
     */
    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                // 监听
                int num =0;
                SocketChannel channel = serverSocketChannel.accept();
                if (null != channel) {
                    num++;
                    EchoHandler echoHandler = new EchoHandler(selector, channel);
                    System.out.println(echoHandler);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
