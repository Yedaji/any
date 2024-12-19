package com.dj.netty.client;

import com.dj.util.Dateutil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * 客户端
 *
 * @author ydj
 * @date 2024/11/20 20:41
 **/
public class FirstNettyClient {
    Bootstrap b = new Bootstrap();
    private int serverPort;
    private String serverIp;

    public static void main(String[] args) {
        new FirstNettyClient("127.0.0.1",9999).runClient();
    }

    FirstNettyClient(String ip, int port) {
        this.serverIp = ip;
        this.serverPort = port;
    }

    public void runClient() {
        // 创建反应器轮询组
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            // 1.设置反应器轮询组
            b.group(eventLoopGroup);
            // 2.设置nio类型的通道
            b.channel(NioSocketChannel.class);
            // 3.设置监听端口
            b.remoteAddress(serverIp, serverPort);
            // 4.设置通道的参数
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            // 5.装配子通道流水线
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 添加handler
                    socketChannel.pipeline().addLast(new NettyEchoClientHandler());
                }
            });
            ChannelFuture future = b.connect();
            future.addListener((listener) -> {
                if (listener.isSuccess()) {
                    System.out.println("EchoClient客户端连接成功！");
                } else {
                    System.out.println("EchoClient客户端连接失败！");
                }
            });
            // 阻塞,直到连接成功
            future.sync();
            Channel channel = future.channel();
            Scanner scanner = new Scanner(System.in);

            System.out.println("请输入发送内容：");
            while (scanner.hasNext()) {
                // 获取输入内容
                String next = scanner.next();
                byte[] bytes = (Dateutil.getNow() + ">>" + next).getBytes();
                // 发送bytebuf
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                System.out.println("请输入发送内容：");
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
        eventLoopGroup.shutdownGracefully();
        }
    }

}


