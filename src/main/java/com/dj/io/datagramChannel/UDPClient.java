package com.dj.io.datagramChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * UDP传输
 *
 * @author ydj
 * @date 2024/11/16 18:24
 **/
public class UDPClient {
    public static void main(String[] args) throws IOException {
        new UDPClient().send();
    }
    public void send() throws IOException {
        // 获取DatagramChannel包
        DatagramChannel dChannel = DatagramChannel.open();
        // 设置为非阻塞
        dChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        System.out.println("UDP客户端启动成功");
        while (scanner.hasNext()) {
            String next = scanner.next();
            buffer.put((new Date() + " >> " + next).getBytes());
            buffer.flip();
            dChannel.send(buffer, new InetSocketAddress("127.0.0.1",19990));
            buffer.clear();
        }
        // 关闭DatagramChannel
        dChannel.close();
        System.out.println("UDP客户端关闭！");
    }
}
