package com.juc.gp;

import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        int size=5;
        MyTest4 myTest4 = new MyTest4(queue,size);
        MyTest5  myTest5 = new MyTest5(queue,size);
        Thread thread = new Thread(myTest4);
        Thread thread1 = new Thread(myTest5);
        thread.start();
        thread1.start();
    }
}
