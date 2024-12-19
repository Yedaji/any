package com.dj.io.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 回显处理器
 *
 * @author ydj
 * @date 2024/11/17 17:37
 **/
public class SendAndPrintHandler implements Runnable {
    int send = 0;
    int read = 1;
    int state = read;
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    // 向打印 再发送到客户端
    SocketChannel socketChannel;
    SelectionKey key;

    public SendAndPrintHandler(Selector selector, SocketChannel c) throws IOException {
        socketChannel =c;
        socketChannel.configureBlocking(false);
        key = socketChannel.register(selector,0,this);
        key.interestOps(SelectionKey.OP_READ);
        // 唤醒
        selector.wakeup();
    }

    @Override
    public void run() {
        if (state == read) {
            //
            try {
                int length = 0;
                while ((length = socketChannel.read(buffer)) > 0) {
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, buffer.limit()));
                    key.interestOps(SelectionKey.OP_WRITE);
                    key.attach(this);// 重复
                    state = send;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            try {
                socketChannel.write(buffer);
                buffer.clear();
                key.interestOps(SelectionKey.OP_READ);
                state = read;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
