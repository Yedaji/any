package com.dj.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 丢弃服务器  DiscardServer
 *
 * @author ydj
 * @date 2024/11/18 20:11
 **/
public class NettyDiscardServer {
    private final int serverPort;
    ServerBootstrap b = new ServerBootstrap();

    public NettyDiscardServer(int port) {
        this.serverPort = port;
    }

    public static void main(String[] args) {
        new NettyDiscardServer(8989).runServer();
    }

    public void runServer(){
        // 创建反应器轮询组
        NioEventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerLoopGroup = new NioEventLoopGroup();
         try {
             // 1.设置反应器轮询组
             b.group(bossLoopGroup,workerLoopGroup);
             // 2.设置nio类型的通道
             b.channel(NioServerSocketChannel.class);
             // 3.设置监听端口
             b.localAddress(serverPort);
             // 4.设置通道参数
             b.option(ChannelOption.SO_KEEPALIVE,true);
             // 5.装配子通道流水线
             b.childHandler(new ChannelInitializer<SocketChannel>() {
                 // 有连接到达时回创建一个通道
                 @Override
                 protected void initChannel(SocketChannel socketChannel) throws Exception {
                     // 流水线的职责，负责管理通道中的处理器
                     // 向子通道传输通道流水线添加一个处理器
                     socketChannel.pipeline().addLast(new NettyDiscardHandler());
                 }
             });

             // 6.开始绑定服务器
             // 通过调用sync同步方法阻塞知道绑定成功
             ChannelFuture channelFuture = b.bind().sync();
             System.out.println("服务器启动成功，监听端口：" + channelFuture.channel().localAddress());
             // 7.等待通道关闭的异步任务结束
             // 服务器监听通道会一直等待通道关闭的异步任务结束
             ChannelFuture closeFuture = channelFuture.channel().closeFuture();
             closeFuture.sync();


         }catch (Exception e){
             System.out.println(e);
         }finally {
             // 8.关闭资源
             workerLoopGroup.shutdownGracefully();
             bossLoopGroup.shutdownGracefully();
         }
    }
}
