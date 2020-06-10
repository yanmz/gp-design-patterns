package com.juc.condition;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer implements  Runnable{
    private Queue<String> str;
    private int maxSize;

    private Lock lock;
    private Condition condition;

    public Consumer(Queue<String> str, int maxSize, Lock lock, Condition condition) {
        this.str = str;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run(){
        int i=0;
        while(true){
            i++;
                lock.lock();
                while (str.isEmpty()){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费消息："+str.remove());
            condition.signal();
            lock.unlock();
        }
    }
}
