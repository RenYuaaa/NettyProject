package com.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: renjiahui
 * @date: 2021-02-15 23:33
 * @description: 案例三：使用一个buffer完成文件读取
 */
public class NIOFileChannel03 {
    private static final Integer NUM_1024 =1024;

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("e:\\file01.txt");

        FileChannel fileChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("e:\\file02.txt");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(NUM_1024);

        while (true) {
            //一定要清除byteBuffer
            byteBuffer.clear();

            int read = fileChannel.read(byteBuffer);

            //-1表示读取完成
            if (read == -1) {
                break;
            }

            byteBuffer.flip();

            //将buffer中的数据写入到fileChannel02中
            outputStreamChannel.write(byteBuffer);
        }

        fileInputStream.close();
        outputStreamChannel.close();
    }
}
