package com.juc.gp;

import java.util.Queue;

public class MyTest4 implements Runnable {
    private Queue<String> queue;
    private int size;
    public int count;

    public MyTest4(Queue<String> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        while (true) {
            count++;
            synchronized (queue) {
                while (queue.size() == size) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.add("生产者" + count);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("生产者生成信息" + count);
                queue.notify();
            }
        }
    }
}
