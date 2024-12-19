package com.dj.io.fileChannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用文件通道复制文件
 *
 * @author ydj
 * @date 2024/11/16 16:02
 **/
public class FileNIOCopyDemo {
    public static void main(String[] args) {
        nioCopyResourceFile();
    }

    /**
     * 复制两个资源目录下的文件
     */
    private static void nioCopyResourceFile() {
        // 原文件
        String sourcePath = "/Users/ydj/code/DataStructures/src/main/java/com/dj/io/channel/文件.txt";
        // 目标文件
        String destPath = "/Users/ydj/code/DataStructures/src/main/java/com/dj/io/channel/复制的文件.txt";
        File srcFile = new File(sourcePath);
        File destFile = new File(destPath);
        try {
            // 如果目标文件不存在，则新建
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            long startTime = System.currentTimeMillis();
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChanel = null;
            FileChannel outChanel = null;
            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                inChanel = fis.getChannel();
                outChanel = fos.getChannel();
                int length = -1;
                // 新街buf，处于写模式
                ByteBuffer buf = ByteBuffer.allocate(1024);
                // 从输入通道读取到buf
                // inChanel.read(buf)： 读取数据并写入到buf中，此时
                while ((length = inChanel.read(buf)) != -1) {
                    // buf第一次模式切换，翻转buf，从写模式变成读模式
                    buf.flip();
                    int outLength = 0;
                    // 将buf写入输出通道
                    // outChanel.write(buf)：将buf的数据写入通道
                    while ((outLength = outChanel.write(buf)) != 0) {
                        System.out.println("写入的字节数 = " + outLength);
                    }
                    buf.clear();
                }
                // 强制刷新到磁盘
                outChanel.force(true);
            } finally {
                // 关流
                outChanel.close();
                fos.close();
                inChanel.close();
                fis.close();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("base复制毫秒数" + (endTime - startTime));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
