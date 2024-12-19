package com.dj.netty.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * 入站处理流程
 *
 * @author ydj
 * @date 2024/11/19 20:57
 **/
public class InPipeline {

    static class SimpleInHandlerA extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("入站处理器A：被回调");
            super.channelRead(ctx, msg);
        }
    }
    static class SimpleInHandlerB extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("入站处理器B：被回调");
            super.channelRead(ctx, msg);
        }
    }
    static class SimpleInHandlerC extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("入站处理器C：被回调");
            super.channelRead(ctx, msg);
        }
    }

    public static void main(String[] args) {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(new SimpleInHandlerA());
                embeddedChannel.pipeline().addLast(new SimpleInHandlerB());
                embeddedChannel.pipeline().addLast(new SimpleInHandlerC());
            }
        };
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(channelInitializer);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(4);
        embeddedChannel.writeInbound(buffer);
    }

}
