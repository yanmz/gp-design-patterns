package org.atguigu.bio.server;

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
public class ServerSocketBlockIOReadDemo {

    /**
     * 连接阻塞
     *
     * @param args
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            //localhost: 8080
            serverSocket = new ServerSocket(8080);
            //加while true只是让服务端处于一直监听，这个server不停机<br>
            while (true) {
                Socket socket = serverSocket.accept(); //监听客户端连接(连接阻塞）被阻塞，连接阻塞<br>
                System.out.println(" ServerSocketIOReadWithThreadPoolDemo：" + socket.getPort());
                System.out.println(" ServerSocket连接accept了");
                handleIo(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //TODO
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void handleIo(Socket socket) {
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//输入流
            String clientStr = bufferedReader.readLine(); //被阻塞了
            //读取客户端的一行数据
            System.out.println("接收到客户端的信息：" + clientStr);
            //写回去
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //不是写到本地而是通过网络写回去<br>
            //注意一定要加\n 表示已经读取完了不然会报connect reset的错误<br>
            bufferedWriter.write("我收到了信息\n");//怎么知道你写完了，不加换行符客户端不知道你处理完了没有<br>
            bufferedWriter.flush();

            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

}
