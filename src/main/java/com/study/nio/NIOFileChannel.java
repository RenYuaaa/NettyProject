package com.study.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: renjiahui
 * @date: 2021-02-15 23:04
 * @description: 案例一：本地文件写数据
 *              使用ByteBuffer（缓冲）和FileChannel，将Hello，NIO写入到file01.txt文件中
 *              文件不存在就创建
 */
public class NIOFileChannel {
    private static final Integer NUM_1024 =1024;

    public static void main(String[] args) throws Exception {
        String str = "Hello NIO";

        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("e:\\file01.txt");

        //通过fileOutputStream 获取对用的FileChannel
        //这个fileChannel的真是类型是 FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(NUM_1024);

        //将str放入到byteBuffer中
        byteBuffer.put(str.getBytes());

        //对byteBuffer进行flip
        byteBuffer.flip();

        //将byteBuffer数据写入到fileChannel中
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
