package org.atguigu.io.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/9/7 17:14
 */
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final  int  PORT =  9999;
    public GroupChatServer(){
        try {
            //选择器
            selector= Selector.open();
            //初始化serverSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",PORT));
            //设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //将serverSocketChannel注册上selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //监听
    public void listen(){
        try {
            //循环处理
            while (true){
                int select = selector.select(2000);
                if(select>0){//有事件处理
                    //遍历得到selectionKey集合
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        //取出SelectionKey
                        SelectionKey key = iterator.next();
                        //监听到sccept
                        if(key.isAcceptable()){
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                            System.out.println(socketChannel.getRemoteAddress()+" 上线 ");
                            socketChannel.configureBlocking(false);
                        }
                        if(key.isReadable()){//通道发送read事件，即通道是可读的状态
                            //处理读
                            readDate(key);
                        }
                        iterator.remove();
                    }
                }else {
                    System.out.println("等待。。。。。");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //读取客户端信息
    private void readDate(SelectionKey key) throws IOException {
        //根据key取到关联的通道
        SocketChannel socketChannel = null;
        try {
             socketChannel = (SocketChannel) key.channel();
            //获取到该channel关联的buffer
            ByteBuffer buffer = (ByteBuffer) key.attachment();

            int read = socketChannel.read(buffer);
            //根据read的值做处理
            if(read>0){
                //把缓冲区的数据转成字符串
                String msg = new String(buffer.array());
                System.out.println("form 客户端："+new String(buffer.array()));
                //向其他客户端转发信息
                sendInfoToOtherClients(msg,socketChannel);
            }
        } catch (IOException e) {
            System.out.println(socketChannel.getRemoteAddress()+"离线了。。。");
            //取消注册
            key.cancel();;
            //关闭通道
            socketChannel.close();
        }
    }

    //转发信息给其他客户（通道）
    private void sendInfoToOtherClients(String msg,SocketChannel self) throws IOException {
        System.out.println("服务转发消息中。。。");
        for (SelectionKey key:selector.keys()){
            //通过key 取出对应的SocketChannel 并排除self
            Channel targetChannel = key.channel();
            //排除自己
            if(targetChannel instanceof SocketChannel && targetChannel!=self){
                //转型
                 SocketChannel dest=(SocketChannel)targetChannel;
                 //将msg存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
