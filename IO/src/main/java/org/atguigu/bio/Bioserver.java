package org.atguigu.bio;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/9/4 10:34
 */

/**
 * BIO阻塞模型 通过线程池优化read阻塞 但是accept()还是阻塞的
 * 客户端通过打开cmd
 * 1.输入telnet 127.0.0.1  6666
 * 2.ctrl+】
 * 3.输入传输内容 send  hhhhhh
 * <p>
 * <p>
 * * 同步阻塞IO，读写阻塞，线程等待时间过长
 * *
 * * 在制定线程策略的时候，只能根据CPU的数目来限定可用线程资源，不能根据连接并发数目来制定，也就是连接有限制。否则很难保证对客户端请求的高效和公平。
 * *
 * * 多线程之间的上下文切换，造成线程使用效率并不高，并且不易扩展
 * *
 * * 状态数据以及其他需要保持一致的数据，需要采用并发同步控制
 */
public class Bioserver {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

        //创建ServerSocket
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("服务端启动。。。。");
            while (true) {
                //监听 等待客户端连接
                Socket accept = serverSocket.accept();
                System.out.println("连接到一个客户端");
                executorService.execute(new ThreadDemo(accept));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadDemo implements Runnable {

    private Socket socket;

    public ThreadDemo(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程信息ID=" + Thread.currentThread().getId() + "  名字=" + Thread.currentThread().getName());
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1025];
            while (true) {
                System.out.println("线程信息ID=" + Thread.currentThread().getId() + "  名字=" + Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
