package com.dj.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * @ChannelHandler.Sharable 共享通道
 *
 * @author ydj
 * @date 2024/11/20 20:24
 **/
@ChannelHandler.Sharable
public class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("msg type："+ (in.hasArray()?"对内存":"直接内存"));
        int len = in.readableBytes();
        byte[] bytes = new byte[len];
        in.getBytes(0,bytes);
        System.out.println("server received: "+ new String(bytes,"UTF-8"));
        System.out.println("回显前，mes.refCnt: "+ ((ByteBuf) msg).refCnt());
        // 写回数据
        ChannelFuture channelFuture = ctx.writeAndFlush(msg);
        channelFuture.addListener((future) ->{
            System.out.println(future);
            System.out.println("写回后："+((ByteBuf) msg).refCnt());
        });
        super.channelRead(ctx, msg);
    }
}
