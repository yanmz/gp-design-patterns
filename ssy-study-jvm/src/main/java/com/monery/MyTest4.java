package com.monery;

public class MyTest4 {
    public static void main(String[] args) {
        new Thread(() -> A.method(), "MethodA").start();
        new Thread(() -> B.method(), "MethodB").start();

    }
}


class A {
    public static synchronized void method() {
        System.out.println("Method A");
        try {
            Thread.sleep(5000);
            B.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class B {
    public static synchronized void method() {
        System.out.println("Method B");
        try {
            Thread.sleep(5000);
            A.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}