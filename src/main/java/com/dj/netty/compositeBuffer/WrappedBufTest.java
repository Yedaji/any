package com.dj.netty.compositeBuffer;

import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author ydj
 * @date 2024/11/20 19:58
 **/
public class WrappedBufTest {
    public static void main(String[] args) {

        CompositeByteBuf cbuf = Unpooled.compositeBuffer();

        cbuf.addComponent(Unpooled.wrappedBuffer(new byte[]{1,2,3}));
        cbuf.addComponent(Unpooled.wrappedBuffer(new byte[]{4}));
        cbuf.addComponent(Unpooled.wrappedBuffer(new byte[]{5,7}));
        // 合并成一个java NIO缓冲区
        ByteBuffer nioBuffer = cbuf.nioBuffer(0, 6);
        byte[] bytes = nioBuffer.array();
        System.out.println("bytes = ");
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        cbuf.release();
    }
}
