package com.study.bio;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author : renjiahui
 * @date : 2021-02-06 22:53
 * @desc : BIO应用实例--创建一个socket服务端接收客户端消息。
 */
public class BioServer {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 创建一个socker服务端
     * 1、使用cmd的telnet 127.0.0.1 6666进行连接
     * 2、使用ctrl + ]进行发送消息
     * 3、发送消息命令如：send hello100
     */
    public static void main(String[] args) throws IOException {
        //创建一个线程池

        //如果有客户端连接，就创建一个线程与之通信（单独写一个方法）
        ExecutorService newCachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        final ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while (true) {

            System.out.println("线程信息  ID = " + Thread.currentThread().getId() +
                    ", 名字是：" + Thread.currentThread().getName());

            System.out.println("等待连接");

            //监听，等待客户端连接
            final Socket accept = serverSocket.accept();

            System.out.println("连接到一个客户端");

            //创建一个线程，与之通讯
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    //可以和客户端通讯
                    handler(accept);
                }
            });
        }
    }


    /**
     * 编写一个handler方法，和客户端通信
     */
    public static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();

            //循环的读取客户端发送的数据
            while (true) {
                System.out.println("线程信息  ID = " + Thread.currentThread().getId() +
                        ", 名字是：" + Thread.currentThread().getName());

                System.out.println("read...");

                int read = inputStream.read(bytes);
                if (read != -1 ) {
                    //输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


