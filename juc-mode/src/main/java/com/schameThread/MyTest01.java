package com.schameThread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MyTest01 extends Thread {
    public static void main(String[] args) {
        MyTest01 myTest01 = new MyTest01();
        myTest01.start();
        for (int i = 0; i < 10000; i++) {
            System.out.print(" Good!");
        }
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.print(" Nice+");
        }
    }
}
