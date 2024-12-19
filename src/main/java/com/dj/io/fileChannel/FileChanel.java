package com.dj.io.fileChannel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ydj
 * @date 2024/11/16 15:42
 **/
public class FileChanel {
    public static void main(String[] args) throws Exception {
        RandomAccessFile rfile = null;

        rfile = new RandomAccessFile("/Users/ydj/code/DataStructures/src/main/java/com/dj/io/channel/文件.txt", "rw");

        FileChannel channel = rfile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int length = -1;
        while ((length=channel.read(byteBuffer))!=-1){
            System.out.println(new String(byteBuffer.array()));
        }
        //切换读模式
        byteBuffer.flip();
        int outlength =0;
        while ((outlength=channel.write(byteBuffer))!=0){
            System.out.println(outlength);
        }
        // 强制刷新到磁盘
        channel.force(true);
        rfile.close();
    }
}
