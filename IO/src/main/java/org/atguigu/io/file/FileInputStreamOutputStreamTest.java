package org.atguigu.io.file;

import java.io.FileInputStream;

/**
 * 测试FileInputStream和FileOutStrema的使用
 * 结论：
 * 1.对于文本文件，使用字符流处理
 * 2.对于非文本文件，使用字节流处理
 *
 * @author yanmz
 * @version 1.0
 * @date 2020/8/18 22:35
 */
public class FileInputStreamOutputStreamTest {

    public static void main(String[] args) throws Exception {
        try (FileInputStream input = new FileInputStream("F:\\Hello World.txt");) {
            byte[] c = new byte[20];
            while (input.read(c) != -1) {
                System.out.println(new String(c));
            }
        } catch (Exception e) {

        }
    }
}
