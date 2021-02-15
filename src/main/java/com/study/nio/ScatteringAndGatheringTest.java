package com.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author: renjiahui
 * @date: 2021-02-16 0:32
 * @description: Scattering: 将数据写入到Buffer是，可以采用Buffer数组，依次写入
 * Gathering：从Buffer读取数据时，可以采用Buffer数组，依次读取
 */
public class ScatteringAndGatheringTest {

    public static void main(String[] args) throws IOException {

        //使用ServerSocketChannel和SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到Socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 8;
        //循环读取
        while (true) {
            int byteRead = 0;

            while (byteRead < messageLength) {
                long read = socketChannel.read(byteBuffers);
                //累计读取的字节数
                byteRead += 1;
                System.out.println("byteRead = " + byteRead);

                //使用流打印，看看当前的这个buffer的position和limit
                Arrays.asList(byteBuffers).stream()
                        .map(e -> "position = " + e.position() + ", limit = " + e.limit())
                        .forEach(System.out::println);
            }

            //将所有的buffer进行flip
            Arrays.asList(byteBuffers).forEach(e -> e.flip());

            //将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }

            //将所有的buffer进行clear
            Arrays.asList(byteBuffers).forEach(e -> e.clear());

            System.out.println("byteRead = " + byteRead + ", byteWrite = " + byteWrite + ", messageLength = " + messageLength);
        }
     }
}
