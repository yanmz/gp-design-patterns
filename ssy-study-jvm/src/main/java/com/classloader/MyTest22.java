package com.classloader;

/**
 * 命令执行：java -Djava.ext.dirs=./ com.classloader.MyTest22
 * 打包成jar：jar cvf test.jar  com/classloader/MyTest1.class
 */
public class MyTest22 {

    static {
        System.out.println("MyTest22 initializer");
    }
    public static void main(String[] args) {
        System.out.println(MyTest22.class.getClassLoader());
        System.out.println(MyTest1.class.getClassLoader());
    }
}
/**
 * MyTest22 initializer
 * sun.misc.Launcher$ExtClassLoader@33909752
 * sun.misc.Launcher$ExtClassLoader@33909752
 */
