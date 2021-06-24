package org.atguigu.io;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/8/17 20:48
 */

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、通道（Channel）:用于源节点与目标节点的连接。在java NIO中负责缓冲区数据的传输，Channel本身不存储数据，因此需要配合缓冲区进行传输
 *
 * 二、通道的主要实现类
 *   java.nio.channels.Channel 接口：
 *     |-- FileChannel
 *     |-- SocketChannel
 *     |-- ServerSocketChannel
 *     |-- DatagramChannel  ---UDP
 *
 * 三、获取通道
 * 1.java针对支持通道的类型提供 getChannel() 方法
 * 本地IO：
 *  FileInputStream/FileOutputStream
 *  RandomAccessFile
 *
 *  网络IO：
 *  Socket
 *  ServerSocket
 *  DatagramSocket
 *
 *  2.在JDK1.7 中的NIO.2 针对各个通道提供了静态方法open()
 *  3.在JDK1.7 中的NIO.2 的Files 工具类newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTO()
 *
 * 五、分散（Scatter） 与聚集（Gather）
 * 分散读取 :将通道中的数据分散到多个缓冲区中
 * 聚集写入：将多个缓冲中的数据聚集到通道中
 *
 *
 * 六、字符串：Charset
 * 编码：字符串--》字节数组
 * 解码：字节数组-》字符串
 */
public class TestChannel {

    //1. 利用通道完成文件的复制(非直接缓冲区)
    @Test
    public void test1() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("1.jpg");
        FileOutputStream fos = new FileOutputStream("2.jpg");

        //1.获取通道
        FileChannel inchannel = fis.getChannel();
        FileChannel outchannel = fos.getChannel();

        //分配指定大小的缓冲区
        ByteBuffer buf =ByteBuffer.allocate(1024);
        //将通道中的数据存入缓冲区中
        while (inchannel.read(buf)!=-1){
            buf.flip();//切换成读取数据的模式
            //将缓冲区中的数据写入通道中
            outchannel.write(buf);
            buf.clear();
        }
        outchannel.close();
        inchannel.close();
        fos.close();
        fis.close();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为："+ (end-start));
    }

    //2.使用直接缓冲区完成文件的复制（内存映射文件）
    @Test
    public void Test2() throws IOException {
        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE_NEW);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY,0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE,0, inChannel.size());


        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为："+ (end-start));
    }

    //通道之间的数据传输（直接缓冲区）
    @Test
    public  void  Test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE_NEW);

        inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel,0,inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    //分散与聚集
    @Test
    public void Test4() throws  Exception {
        RandomAccessFile raf1 = new RandomAccessFile("1.txt","rw");
        //获取通道
        FileChannel channel = raf1.getChannel();

        //分配指定大小的缓冲区
        ByteBuffer buf1 =  ByteBuffer.allocate(100);
        ByteBuffer buf2 =  ByteBuffer.allocate(1024);

        //分散读取
         ByteBuffer[] bufs = {buf1,buf2};
         channel.read(bufs);

         for (ByteBuffer buf: bufs){
                buf.flip();
         }

        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println("---------");
        System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));

        //聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt","rw");
        FileChannel channel1 = raf2.getChannel();
        channel1.write(bufs);
    }

    //字符集
    @Test
    public void Test6() throws CharacterCodingException {
        Charset cs1  = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder ce =cs1.newEncoder();

        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();
        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("的舅舅家的撒娇");
        cBuf.flip();

        //编码
        ByteBuffer bBuf = ce.encode(cBuf);
        for (int i=0;i<12;i++){
            System.out.println(bBuf.get());
        }
        //解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        System.out.println("---------------------------");

        Charset cs2 = Charset.forName("GBK");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());
    }
}
