package org.atguigu.io.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/9/8 9:51
 */
public class GroupChatClient {
    //定义相关属性
    private static final  String  HOST ="127.0.0.1";
    private static final  int PROT = 9999;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;
    public GroupChatClient() throws IOException {
        selector = Selector.open();
        //连接服务器
        socketChannel = SocketChannel.open().bind(new InetSocketAddress(HOST,PROT));
        socketChannel.configureBlocking(false);
        //将channel注册到selector
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到username
        username = socketChannel.getLocalAddress().toString();
        System.out.println(username+ " 准备好了");
    }

    //向服务器发送信息
    public  void sendInfo(String info){
        info = username+" 说："+info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取服务端回复的信息
    public void readInfo(){
            try {
                int select = selector.select();
                if (select>0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        if(key.isReadable()){
                            //得到相关通道
                            SocketChannel channel = (SocketChannel)key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            //读取
                            int read = channel.read(buffer);
                            String msg = new String(buffer.array());
                            System.out.println(msg.trim());
                        }
                    }
                    iterator.remove();
                }else {
                    System.out.println("没有可用的通道。。。");
                }
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        final GroupChatClient groupChatClient = new GroupChatClient();

         new Thread(){
             @Override
             public void run() {
                  while (true){
                      groupChatClient.readInfo();
                      try {
                          Thread.sleep(3000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
             }
         }.start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            groupChatClient.sendInfo(s);
        }
    }
}
