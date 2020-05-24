package com.juc.gp;

import java.io.File;

public class MyTest7 {
    static Boolean flag = false;
    static int i = 10;


    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            count();
        },"other-thread").start();

        Thread.sleep(1000);

        flag = true;

    }
    static void count() {
        while (!flag) {
//            new File("");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        System.out.println(i);
    }
}
