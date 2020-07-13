package com.juc.gp;

import java.util.Queue;

public class MyTest5 implements Runnable {
    private Queue<String> queue;
    private int size;
    public int count;

    public MyTest5(Queue<String> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        while (true) {
            count++;
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.remove();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费信息" + count);
                queue.notify();
            }
        }
    }
}
