package org.atguigu.io;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/8/17 17:05
 */

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在java NIO中 负责数据的存取，缓冲区就是数组，用于存储不同数据类型的数据
 * 根据数据类型不同（boolean 除外）提供了相应类型的缓冲区：
 * ByteBuffer、 CharBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer
 *
 * 上述缓冲区 的管理方法几乎一致，通过allocate() 获取缓冲区
 *
 * 二、缓冲区存取数据的两个核心方法
 * put(): 存入数据到缓冲区
 * get():获取缓冲区中的数据
 *
 * 三、缓冲区中的四个核心属性
 * capacity：容量，表示缓冲区中最大存储数据的容量，一但声明不能改变
 * limit：界限，表示缓存区中可以操作数据的大小（limit 后数据不能进行读写）
 * position：位置，表示缓冲区中正在操作数据的位置
 * mark <= position <= limit <= capacity
 *
 * 四、直接缓冲区与非直接缓冲区
 * 直接缓冲区：通过allocatedirect() 方法分配直接缓冲区，将缓冲区建立物理内存中，可以提高效率
 * 非直接缓冲区：通过allocate() 方法分配缓冲区，将缓冲区建立在JVM的内存中
 */
public class TestBuffer {

    @Test
    public void Test1(){
        String str="abcde";

        //1.分配一个指定大小的缓冲区
        ByteBuffer buf  = ByteBuffer.allocate(1024);
        System.out.println("------allocate()----------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2.利用put() 存入数据到缓冲区中
        System.out.println("------put()----------");
        buf.put(str.getBytes());
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3.切换读取数据模式
        System.out.println("------flip()----------");
        buf.flip();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());


        //4.利用get()读取缓冲区中的数据
        System.out.println("------get()-------------");
        byte[]  dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst,0,dst.length));

        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5.rewind():可重复读
        System.out.println("------------rewind()--------------");
        buf.rewind();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());


        //6.clear():清空缓冲区，但是缓冲区中的数据依然存在，但是处于“被遗忘”的状态
        System.out.println("------------clear()--------------");
        buf.clear();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println((char) buf.get());

    }


    @Test
    public void test1(){
        String str1="abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str1.getBytes());
        buf.flip();
        byte[] b = new byte[buf.limit()];
        buf.get(b,0,2);
        System.out.println(new String(b,0,2));
        System.out.println(buf.position());

        //mark()  标记
        buf.mark();

        buf.get(b,2,2);
        System.out.println(new String (b,2,2));
        System.out.println(buf.position());

        //reset():恢复到mark的位置
        buf.reset();
        System.out.println(buf.position());
    }
}
