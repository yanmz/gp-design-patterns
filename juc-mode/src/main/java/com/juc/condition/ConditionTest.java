package com.juc.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        int maxSize = 5;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Product product = new Product(queue,maxSize,lock,condition);
        Consumer consumer = new Consumer(queue,maxSize,lock,condition);
        Thread thread1 = new Thread(product);
        thread1.start();
        thread1.setName("线程product");

        Thread thread2 = new  Thread(consumer);
        thread2.start();
        thread2.setName("线程consumer");
    }
}
