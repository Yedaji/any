package com.dj.netty.compositeBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * 组合ByteBuf零拷贝
 *
 * @author ydj
 * @date 2024/11/20 19:35
 **/
public class CompositeBuffTest {
    static Charset utf8 = Charset.forName("UTF-8");

    public static void main(String[] args) {
        CompositeByteBuf cbuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        // 消息头
        ByteBuf headBuff = Unpooled.copiedBuffer("这是一个头", utf8);
        // 消息体
        ByteBuf bodyBuff = Unpooled.copiedBuffer("高性能netty", utf8);
        cbuf.addComponents(headBuff, bodyBuff);
        sendMsg(cbuf);
        headBuff.retain();
        cbuf.release();
        // 复用消息体
        cbuf =ByteBufAllocator.DEFAULT.compositeBuffer();
        bodyBuff = Unpooled.copiedBuffer("这还是第二个体", utf8);
        cbuf.addComponents(headBuff, bodyBuff);
        sendMsg(cbuf);
        cbuf.release();
    }

    private static void sendMsg(CompositeByteBuf cbuf) {
        for (ByteBuf byteBuf : cbuf) {

            int length = byteBuf.readableBytes();
            byte[] bytes = new byte[length];
            // 将CompositeByteBuf中的数据统一复制到数组中
            byteBuf.getBytes(byteBuf.readerIndex(), bytes);
            // 打印数据
            System.out.println(new String(bytes, utf8));
        }
        System.out.println();
    }

}
