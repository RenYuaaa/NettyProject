package com.study.nio;

import java.nio.ByteBuffer;

/**
 * @author: renjiahui
 * @date: 2021-02-16 0:07
 * @description:
 */
public class NIOByteBufferPutGet {

    public static void main(String[] args) {

        //创建一个Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        byteBuffer.putInt(100);
        byteBuffer.putLong(9L);
        byteBuffer.putChar('人');
        byteBuffer.putShort((short) 4);

        //取出
        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
    }
}
