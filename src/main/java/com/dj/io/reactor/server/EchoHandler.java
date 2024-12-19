package com.dj.io.reactor.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 回显处理器
 *
 * @author ydj
 * @date 2024/11/17 14:25
 **/
public class EchoHandler implements Runnable {
    static final int RECEIVING = 0;
    static final int SENDING = 1;
    final SocketChannel channel;
    final SelectionKey key;
    final ByteBuffer buffer = ByteBuffer.allocate(1024);
    int state = RECEIVING;

    public EchoHandler(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        channel.configureBlocking(false);
        // 设置关注IO事件
        key = channel.register(selector, 0);
        // 将Handler自身作为选择键的附件，一个连接对应一个处理器实例
        key.attach(this);
        // 注册read就绪事件
        key.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }
    // 构造器

    @Override
    public void run() {
        try {
            if (state == SENDING) {
                //发送状态，把数据写入连接通道
                channel.write(buffer);
                // 切换写模式
                buffer.clear();
                // 注册read就绪事件，开始接收客户端数据
                key.interestOps(SelectionKey.OP_READ);
                // 修改状态，从通道读取数据
                state = RECEIVING;
            } else if (state == RECEIVING) {
                // 接收状态，从通道读取数据
                int length = 0;
                while ((length = channel.read(buffer)) > 0) {
                    System.out.println(new String(buffer.array(), 0, length));
                }
                // 读完后，翻转为读模式
                buffer.flip();
                // 准备写入数据到通道，注册write就绪事件
                key.interestOps(SelectionKey.OP_WRITE);
                // 注册完成，进入发送状态
                state = SENDING;
            }
            // 处理结束不关闭key
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
