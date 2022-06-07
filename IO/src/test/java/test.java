import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/8/31 16:19
 */
public class test {

    //客户端
    @Test
    public void client() throws IOException {
        //1.获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        //2. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3.读取本地文件并发送到服务器
        while (inChannel.read(buf) != -1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        sChannel.shutdownOutput();

        int len = 0;
        while ((len = sChannel.read(buf)) != -1) {
            buf.flip();
            System.out.println(new String(buf.array(), 0, len));
            buf.clear();
        }


        //4.关闭通话
        inChannel.close();
        sChannel.close();
    }

    @Test
    public void server() throws IOException {
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //2.绑定连接
        ssChannel.bind(new InetSocketAddress(9898));


        ByteBuffer buf = ByteBuffer.allocate(1024);
        //3.获取客户端连接的通道
        SocketChannel accept = ssChannel.accept();

        //4.接收客户端的数据 并保存到本地
        while (accept.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        accept.write(ByteBuffer.wrap("服务端接收到了客户端请求".getBytes()));

        //6.关闭通道
        ssChannel.close();
        outChannel.close();
        accept.close();

    }
}
