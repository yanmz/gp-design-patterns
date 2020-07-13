package com.classloader;

/**
 * 准备阶段和初始化的顺序问题
 */
public class MyTest6 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println("count1:" + Singleton.count1);
        System.out.println("count1:" + Singleton.count2);
    }
}

class Singleton {
    public static int count1 = 0;
    public static int count2;

    public static Singleton singleton = new Singleton();

    public Singleton() {
        count1++;
        count2++;
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}
