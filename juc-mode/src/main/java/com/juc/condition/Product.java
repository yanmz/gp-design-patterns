package com.juc.condition;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Product  implements  Runnable{
        private Queue<String> str;
        private int maxSize;
        Lock lock;
        Condition condition;

    public Product(Queue<String> str, int maxSize, Lock lock, Condition condition) {
        this.str = str;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
     int  i = 0;
     while(true){
        i++;
        lock.lock();
        while(str.size()==maxSize){
            System.out.println("生产者队列满了，先等待");
            try {
                condition.await();//线程释放当前锁并等待，相当于把线程挂起
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         System.out.println("生产消息："+i);
         str.add("生产者生产信息"+i);
         condition.signal();//唤醒被阻塞线程
         lock.unlock();
     }
    }
}
