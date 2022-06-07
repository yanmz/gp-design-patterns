package org.atguigu.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/9/7 15:54
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //提供服务端的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("11111111111111111111111");
            }
        }
        //如果连接成功，就发送数据
        String str = "hello 尚硅谷";

        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());

        //发送数据，将buffer 数据写入channel
        socketChannel.write(buffer);
        System.in.read();
    }
}
