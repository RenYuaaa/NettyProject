package com.study.nio;

import java.nio.ByteBuffer;

/**
 * @author: renjiahui
 * @date: 2021-02-16 0:16
 * @description:
 */
public class ReadOnlyBuffer {

    public static void main(String[] args) {

        //创建一个Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        for (int i = 0; i < 64; i++) {
            byteBuffer.put((byte) i);
        }

        //读取
        byteBuffer.flip();

        //得到一个只读的Buffer
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        //读取
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

        //对于只读的Buffer，如果放入数据会抛出ReadOnlyBufferException异常
//        readOnlyBuffer.put((byte)100);
    }
}
