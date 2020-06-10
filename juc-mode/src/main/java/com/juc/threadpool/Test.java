package com.juc.threadpool;

import java.util.concurrent.ExecutorService;

public class Test implements Runnable {
    private static ExecutorService es = Demo1.newCachedThreadPool();

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1; i++) {
            es.execute(new Test());
        }
        es.shutdown();
    }
}