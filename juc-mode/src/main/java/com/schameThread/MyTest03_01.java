package com.schameThread;

public class MyTest03_01 {
    public static void main(String[] args) {
        MyTest03 myTest03 = new MyTest03(1000, "张三");
        Thread t = new Thread(() -> {
            myTest03.deposit(100);
            myTest03.withdraw(900);
            System.out.println(myTest03);
        }, "Thread_01");
        t.start();


        new Thread(() -> {
            myTest03.deposit(100);
            myTest03.withdraw(900);
            System.out.println(myTest03);
        }, "Thread_02").start();
    }
}
