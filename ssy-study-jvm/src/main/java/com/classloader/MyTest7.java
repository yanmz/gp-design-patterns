package com.classloader;

public class MyTest7 {
    public static void main(String[] args) throws  Exception{
        Class clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());

        Class clacc = Class.forName("com.classloader.c");
        System.out.println(clacc.getClassLoader());
    }
}
class c{

}
