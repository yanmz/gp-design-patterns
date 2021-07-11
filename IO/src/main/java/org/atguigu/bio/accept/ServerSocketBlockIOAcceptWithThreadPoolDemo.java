package org.atguigu.bio.accept;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class ServerSocketBlockIOAcceptWithThreadPoolDemo {

    static ExecutorService executorService= Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        try {
           final  ServerSocket serverSocket=new ServerSocket(8088);
//            for(int i = 0; i<3;i++)
//            {
//               Thread t =  new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        handleConnectAndRequest(serverSocket);
//                    }
//                },"serverThread_" + i);
//               t.start();
//               t.join();
//            }
            Thread t1 =  new Thread(new Runnable() {
                @Override
                public void run() {
                    handleConnectAndRequest(serverSocket);
                }
            },"serverThread_1");
            t1.start();

            Thread t2 =  new Thread(new Runnable() {
                @Override
                public void run() {
                    handleConnectAndRequest(serverSocket);
                }
            },"serverThread_2");
            t2.start();

            Thread t3 =  new Thread(new Runnable() {
                @Override
                public void run() {
                    handleConnectAndRequest(serverSocket);
                }
            },"serverThread_3");
            t3.start();

            t1.join();
            t2.join();
            t3.join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 执行结果：从下面ServerSocketDemo-accept了可以看出可以接收多个连接（只有一个ServerSocket）
     * serverThread_1 ServerSocketDemo开始等待连接：
     * serverThread_2 ServerSocketDemo开始等待连接：
     * serverThread_3 ServerSocketDemo开始等待连接：
     * serverThread_1 ServerSocketIOReadWithThreadPoolDemo-accept了：49790
     * serverThread_3 ServerSocketIOReadWithThreadPoolDemo-accept了：49789
     * serverThread_2 ServerSocketIOReadWithThreadPoolDemo-accept了：49791
     * serverThread_3开始readLine
     * serverThread_1开始readLine
     * serverThread_2开始readLine
     * serverThread_3 接收到客户端的信息：thread_0 SocketClientDemo：我是客户端，给服务端发送一个消息
     * serverThread_1 接收到客户端的信息：thread_1 SocketClientDemo：我是客户端，给服务端发送一个消息
     * serverThread_2 接收到客户端的信息：thread_2 SocketClientDemo：我是客户端，给服务端发送一个消息
     */

    public static void  handleConnectAndRequest(ServerSocket serverSocket)
    {
        try {
            //localhost: 8080
//            serverSocket=new ServerSocket(8080); //java.net.BindException: Address already in use: JVM_Bind
            //加while true只是让服务端处于一直监听，这个server不停机<br>
            String threadName = Thread.currentThread().getName();
            while(true) {
                System.out.println(threadName + " ServerSocketDemo开始等待连接：");

                Socket socket = serverSocket.accept(); //监听客户端连接(连接阻塞）被阻塞，连接阻塞<br>
                System.out.println(threadName + " ServerSocketIOReadWithThreadPoolDemo-accept了：" + socket.getPort());
                Thread.sleep(1000);
                handleIo(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //TODO
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static  void handleIo(Socket socket){
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "开始readLine");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//输入流
            String s = bufferedReader.readLine(); //被阻塞了
            String clientStr = s; //读取客户端的一行数据
            System.out.println(threadName+ " 接收到客户端的信息：" + clientStr);
            //写回去
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //不是写到本地而是通过网络写回去<br>
            bufferedWriter.write(threadName + " 我收到了信息\n");//怎么知道你写完了，不加换行符客户端不知道你处理完了没有<br>
            bufferedWriter.flush();

            bufferedReader.close();
            bufferedWriter.close();
        }catch (Exception e)
        {
            System.out.println(e.getCause());
        }
    }

}
