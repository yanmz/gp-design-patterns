package com.juc.countdownltch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 可看作是计数器
 */
public class MyTest1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--执行中");
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--执行中");
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--执行中");
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
    }
}
