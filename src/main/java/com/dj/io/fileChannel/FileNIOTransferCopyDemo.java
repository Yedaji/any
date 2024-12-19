package com.dj.io.fileChannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 更快的复制
 *
 * @author ydj
 * @date 2024/11/16 16:50
 **/
public class FileNIOTransferCopyDemo {
    public static void main(String[] args) {
        fileCopy();
    }

    private static void fileCopy() {
        String srcPath = "/Users/ydj/code/DataStructures/src/main/java/com/dj/io/channel/文件.txt";
        String destPath = "/Users/ydj/code/DataStructures/src/main/java/com/dj/io/channel/文件3.txt";
        try {
            long start = System.currentTimeMillis();
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outChanel = null;
            try {
                fis = new FileInputStream(srcFile);
                inChannel = fis.getChannel();
                fos = new FileOutputStream(destFile);
                outChanel = fos.getChannel();
                long pos = 0; //偏移量
                long count = 0;
                long size = inChannel.size();
                while (size > pos) {
                    count = size < 1024 ? 1024 : size;
//                    pos = pos + outChanel.transferFrom(inChannel, pos, count);
                    long length = inChannel.transferTo(pos, count, outChanel);
                    pos +=length;
                }
            } finally {
                fos.close();
                outChanel.close();
                fis.close();
                inChannel.close();

            }
            System.out.println(System.currentTimeMillis() - start);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
