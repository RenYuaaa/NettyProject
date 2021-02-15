package com.study.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: renjiahui
 * @date: 2021-02-16 0:22
 * @description: 说明：
 * 1、MappedByteBuffer可让文件直接在内存（堆外内存）修改，操作系统不需要拷贝一次
 * 操作系统级别的修改，速度较快
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("e:\\1.txt", "rw");

        //获取对应的Channel
        FileChannel fileChannel = randomAccessFile.getChannel();

        /**
         * 参数说明：
         *  参数一：FileChannel.MapMode.READ_WRITE  --使用的读写模式
         *  参数二：0 --可以直接修改的起始位置
         *  参数三：5 -- 是映射到内存的到校，即将1.txt的多少个字节映射到内存，可以修改的范围就是0~5，前闭后开
         */
        MappedByteBuffer mappedByteBuffer = fileChannel
                .map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte)'H');
        mappedByteBuffer.put(3, (byte)'9');

        randomAccessFile.close();
        System.out.println("修改成功");
    }
}
