package com.dj.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.ByteBuffer;

/**
 * @author ydj
 * @date 2024/11/18 21:20
 **/
public class NettyDiscardHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
       try {
           System.out.println("收到消息，丢弃如下：");
           while (in.isReadable()){
               System.out.println((char) in.readByte());
           }
           System.out.println();
       }finally {
           ReferenceCountUtil.release(msg);
       }
    }
}
