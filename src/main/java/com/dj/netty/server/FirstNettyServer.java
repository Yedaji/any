package com.dj.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端--读取客户端消息并回显到客户端
 *
 * @author ydj
 * @date 2024/11/20 20:18
 **/
public class FirstNettyServer {
    public static void main(String[] args) {
        new FirstNettyServer().runServer();
    }

    public void runServer() {

        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup bossLoop = new NioEventLoopGroup();
        NioEventLoopGroup workLoop = new NioEventLoopGroup();
        try {
            bootstrap.group(bossLoop, workLoop);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(9999);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyEchoServerHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.bind().sync();
            System.out.println("服务器启动成功，监听端口：" + channelFuture.channel().localAddress());
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workLoop.shutdownGracefully();
            bossLoop.shutdownGracefully();
        }
    }
}
