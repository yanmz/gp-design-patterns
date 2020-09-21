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
 * BIO模型
 * 客户端通过打开cmd
 * 1.输入telnet 127.0.0.1  6666
 * 2.ctrl+】
 * 3.输入传输内容 send  hhhhhh
 */
public class Bioserver {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

        //创建ServerSocket
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("服务端启动。。。。");
            while (true){
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

class  ThreadDemo  implements Runnable{

    private Socket socket;

    public  ThreadDemo(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程信息ID="+Thread.currentThread().getId()+"  名字="+ Thread.currentThread().getName());
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1025];
            while (true){
                System.out.println("线程信息ID="+Thread.currentThread().getId()+"  名字="+ Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if(read!=-1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
