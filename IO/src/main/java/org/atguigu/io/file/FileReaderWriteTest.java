package org.atguigu.io.file;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/8/18 21:46
 */
public class FileReaderWriteTest {
    public static void main(String[] args) {
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());
    }

    /**
     * 文件写入
     *
     * @throws IOException
     */
    @Test
    public void testFileReader() throws IOException {
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());
        FileReader fr = new FileReader(file);
        //方式一：
        try {
            //3.数据读入
            //read():返回读入的一个字符。如果达到文件末尾 返回-1
            int read = fr.read();
            while (read != -1) {
                System.out.print((char) read);
                read = fr.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fr.close();
        }

        //方式二：
        int data = 0;
        while ((data = fr.read()) != -1) {
            System.out.println((char) data);
        }
    }


    @Test
    public void testFileReader1() throws IOException {
        //File实例化
        File file = new File("hello.txt");
        //FileReader流的实例化
        FileReader fileReader = new FileReader(file);

        //3.流的读入
        char[] chars = new char[10];
        int len = 0;
        while ((len = fileReader.read(chars)) != -1) {
            for (char c : chars) {
                System.out.print(c);
            }
        }
    }

    /**
     * 文件写出
     */

    @Test
    public void testFileWrite() throws IOException {
        //提供File类的对象，指明写出到的文件
        File file = new File("hello1.txt");

        //提供FileWrite的对象，用于数据的写出
        FileWriter fileWriter = new FileWriter(file);

        //写出操作
        fileWriter.write("asvdcsjzdxjcnsdhzbdx");
        //流关闭
        fileWriter.close();
    }

    @Test
    public void testFileReaderFileWrite() throws IOException {
        //创建File类的对象，指明读入和写出的文件
        File srcFile = new File("hello.txt");
        File destFile = new File("hello2.txt");
        //创建输入流和输出流的对象
        FileReader fr = new FileReader(srcFile);
        FileWriter fw = new FileWriter(destFile);
        //数据的读入和写出操作
        char[] c = new char[10];
        int len;//记录每次读入到c数组的字符个数

        while ((len = fr.read(c)) != -1) {
            fw.write(c, 0, len);
        }
        //关闭流
        fw.close();
        fr.close();
    }
}
