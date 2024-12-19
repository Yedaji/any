package com.dj.io.discard;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Dicard客户端
 *
 * @author ydj
 * @date 2024/11/17 10:22
 **/
public class NioDiscardClient {
    public static void main(String[] args) throws IOException {
        startClient();
    }

    public static void startClient() throws IOException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        // 1.获取通道
        SocketChannel socketChannel = SocketChannel.open(address);
        // 2. 切换非阻塞模式
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect()){
            // 不断的自旋等连接
        }
        // 3. 分配指定大小缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello world".getBytes());
        buffer.flip();
        // 4.发送到服务器
        socketChannel.write(buffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }
}
