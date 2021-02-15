package com.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author: renjiahui
 * @date: 2021-02-16 0:01
 * @description: 案例四：拷贝文件--transferFrom方法
 */
public class NIOFileChannel04 {

    public static void main(String[] args) throws Exception {

        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("e:\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("e:\\b.jpg");

        //获取各个流对应的fileChannel
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        //使用transferFrom完成拷贝
        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

        //关闭相关通道和流
        inputStreamChannel.close();
        outputStreamChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
