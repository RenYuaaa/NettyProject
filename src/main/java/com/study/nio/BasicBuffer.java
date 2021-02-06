package com.study.nio;

import java.nio.IntBuffer;

/**
 * @author : renjiahui
 * @date : 2021-02-06 23:54
 * @desc : 一个Buffer的基本案例
 */
public class BasicBuffer {

    public static void main(String[] args) {

        //举例说明buffer的使用（简单说明）
        //创建一个buffer，大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer中存放数据
        intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);

        //如何从buffer中读取数据
        //将buffer转换，读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }

    }

}
