package org.atguigu.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/9/7 14:51
 */
public class NioServer {
    public static void main(String[] args) throws Exception {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个Socket对象
        Selector selector = Selector.open();

        //绑定一个端口6666 在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //把serverSocketChannel注册到selector关心事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (selector.select() > 0) {
            //这里我们等待1秒 如果没有事件发生 返回
            if (selector.select(1000) == 0) {//没有事件发生
                System.out.println("服务器等待1秒 ，无连接");
                continue;
            }

            //如果返回>0,就获取到相关的SelectionKey集合
            //1.如果返回>0,表示已经获取到关注事件
            //2.selector.selectedKeys() 返回关注事件的集合
            // 通过selectedKeys反向获取通道
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                // 获取到selectionKey
                SelectionKey key = iterator.next();

                //根据key  获取对应通道发生的事件做相应处理
                if (key.isAcceptable()) {//如果是 OP_ACCEPT，有新的客户端连接
                    //该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功 生成了一个socketChannel " + socketChannel.hashCode());

                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector，关注事件为OP_ACCEP，同时给socketChannel关联一个BUffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()) {//发生OP_READ
                    //通过key 反向获取到 对应的channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端" + new String(buffer.array()));
                }
                //手动从集合中移动当前的selectionKey，防止重复操作
                iterator.remove();
            }
        }
    }
}
