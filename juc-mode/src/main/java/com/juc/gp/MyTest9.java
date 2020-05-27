package com.juc.gp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTest9 {
    static  int count =0;
   static Lock lock = new ReentrantLock();

    public static  void inc(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        count++;
        lock.unlock();
    }

    public static void main(String[] args) {
        for(int i =0;i<1000;i++){
        Thread thread  = new Thread(()->{
                count++;
        },"test_01");
            thread.start();
    }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
