package com.classloader;

/**
 * 递归查找打印类加载器
 */
public class MyTest13 {
    public static void main(String[] args) {
        ClassLoader classLoader1 = MyTest13.class.getClassLoader();//获得当前类加载器
        System.out.println(classLoader1);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        while (null !=classLoader){
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}

