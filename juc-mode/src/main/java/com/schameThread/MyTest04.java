package com.schameThread;

public class MyTest04 extends Thread {
    public static void main(String[] args) {
        MyTest04 myTest04 = new MyTest04();
        myTest04.start();
    }

    @Override
    public void run() {
        System.out.println("Thread");
    }
}
