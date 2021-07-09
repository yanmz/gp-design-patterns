package org.atguigu.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * NIO非阻塞网络编程原理
 * 1.首先服务端有一个ServerSocketChannel，默认阻塞式，设置为非阻塞模式
 * 2.服务端需要有一个Selector统一监听所有的Channel
 * 3.服务端将ServerSocketChannel注册到Selector上（注册时选择对应的事件类型）会生成一个SelectionKey
 * 4.当客户端连接时，服务端Selector会监听到这个事件，通过对应的SelectionKey获取对应的ServerSocketChannel，从而得到SocketChannel
 * 5.SocketChannel读取客户端发送过来的数据
 * 6.关闭通道
 * <p>
 * PS:
 * selector.keys()是获取所有的key
 * selector.selectedKeys()是获取有事件的key
 *
 * @author zhangfan
 */
public class SelectorDemo01 {
    @Test
    public void server() throws Exception {
//1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//2.切换非阻塞模式
        serverSocketChannel.configureBlocking(false);
//3.绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9527));
//4.创建选择器
        Selector selector = Selector.open();
//5.将通道注册到选择器上，指定"监听接收事件"选择键
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//6.轮询式获取选择器上已"准备就绪"的事件
        while (selector.select() > 0) {
//7.获取当前选择器中所有注册的"选择键(已就绪的监听事件)"
            Iterator<SelectionKey> iterator  = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
//8.获取准备就绪的事件
                SelectionKey key = iterator.next();
//9.判断就绪的事件是什么类型
                if (key.isAcceptable()) {
//10.如果是“接收事件”就绪，获取客户端连接通道
                    SocketChannel channel = serverSocketChannel.accept();
//11.切换非阻塞模式（这一步不做，客户端发送数据过来的时候会抛异常）
                    channel.configureBlocking(false);
//12.将该通道注册到选择器上（接下来要读通道里的数据，所以用读选择键）
//PS：这里可以传第三个参数，指定一个缓冲区
                    channel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024 * 8));
                } else if (key.isReadable()) {
//13.获取当前选择器上"读就绪"状态的通道
                    SocketChannel channel = (SocketChannel) key.channel();
//14.读取数据
// ByteBuffer buffer = ByteBuffer.allocate(1024 * 8); 上面12传了第三个参数，就可以直接获取buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    int len = -1;
                    while ((len = channel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
//15.key用完了要删除，防止重复操作同一个key对应的Channel
                iterator.remove();
            }
        }
    }

    /**
     * open()直接指定连接服务器开启连接，这个没有用选择器，
     * 参考practice中的ChatClient，用选择器来监听事件
     *
     * @throws IOException
     */
    @Test
    public void client01() throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9527));
        channel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.next();
            buffer.put(msg.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        }
        channel.close();
    }

    /**
     * open()不传参数，后面手动开启连接的方式
     * <p>
     * connect(address)这个方法针对阻塞式和非阻塞式IO的处理不一样
     * 1、阻塞式：如果通道是阻塞式，则此方法的调用将阻塞，直到建立连接或发生I / O错误。
     * 2、非阻塞式：如果此通道处于非阻塞模式，则调用此方法将启动非阻塞连接操作。
     * 如果立即建立连接（如本地连接可能发生），则此方法返回true 。 否则，此方法返回false ，
     * 稍后必须通过调用finishConnect()方法完成连接操作。
     * PS：isConnectionPending()判断此通道上的连接操作是否正在进行中。
     * 返回结果true当且仅当在此通道上启动了连接操作但尚未通过调用 finishConnect()方法完成
     */
    @Test
    public void client02() throws Exception {
        SocketChannel channel = null;
        final InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9527);
        try {
            channel = SocketChannel.open();
            channel.configureBlocking(false);
//connect()是手动调用连接，但是不一定会成功
            channel.connect(address);
            Scanner scanner = new Scanner(System.in);
            while (true) {
//非阻塞式必须要加这一步finishConnect()，成功连接就返回true
                if (channel.finishConnect()) {
                    if (channel.isConnected()) {
                        String msg = scanner.next();
//将消息直接包裹到buffer中，也不用flip，直接就可以写出到通道中了
                        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
                        System.out.println(buffer.position());
                        System.out.println(buffer.limit());
                        System.out.println(buffer.capacity());
                        channel.write(buffer);
                        buffer.clear();
                    }
                }
            }
        } finally {
            if (channel != null) {
                channel.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SelectorDemo01 object = new SelectorDemo01();
        object.client02();
    }
}