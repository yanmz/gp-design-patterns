package com.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTest1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new a());
        executorService.shutdown();
    }
}

class a implements Runnable {
    @Override
    public void run() {
        System.out.println("11111");
    }
}
