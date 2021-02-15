package com.study.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: renjiahui
 * @date: 2021-02-15 23:21
 * @description: 案例二：
 *               使用ByteBuffer和FileChannel，将file01.txt中的数据读入到程序中，并显示在控制台屏幕上
 */
public class NIOFileChannel02 {

    public static void main(String[] args) throws Exception {

        //创建文件输入流
        File file = new File("e:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //通过fileInputStream获取对应的fileChannel -> 实际类型 FileChannelImpl
        FileChannel channel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //将通道的数据写入到Buffer中
        channel.read(byteBuffer);

        //将ByteBuffer的字节数据转成String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
