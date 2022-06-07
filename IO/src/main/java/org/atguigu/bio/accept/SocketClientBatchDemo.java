package org.atguigu.bio.accept;

import cn.hutool.core.date.DateUtil;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class SocketClientBatchDemo {

    private static void requestToServer() {
        try {
            String threadName = Thread.currentThread().getName();
            Socket socket = new Socket("localhost", 8080);
            System.out.println(threadName + " SocketClientDemo：去连接serverSocket了");
            Thread.sleep(10000);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(threadName + " SocketClientDemo：我是客户端，给服务端发送一个消息\n");
            bufferedWriter.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//输入流
            String nowTimeStr = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            System.out.println(threadName + nowTimeStr + " SocketClientDemo：客户端开始 readLine");
            String serverLine = bufferedReader.readLine(); //读取服务端返回的数据（被阻塞了），如果serverSocket.sleep(20秒)的话这里就要等20s
            System.out.println(threadName + nowTimeStr + " SocketClientDemo：我接收到了服务端反馈的数据:" + serverLine);

            /**
             * java.net.SocketException: Connection reset
             * 	at java.net.SocketInputStream.read(SocketInputStream.java:210)，报这个错误的原因是客户端不知道server是否写完了<br>
             * 	所以在socketDemo工程里必须写到客户端的数据必须加 "\n"，即类似于下面的语句解决了Connection reset的错误：
             * 	bufferedWriter.write((new Date() + ":服务端接收到了\n"));
             */
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final CountDownLatch cdl = new CountDownLatch(1);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cdl.await(); //阻塞线程| 1000个线程阻塞
                        System.out.println("线程_" + Thread.currentThread().getName() + "开始往服务器请求!");
                        requestToServer();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "thread_" + i).start();
        }
        cdl.countDown();
    }
}
