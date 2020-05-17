package com.juc.gp;

import java.util.concurrent.TimeUnit;

/**
 * timed_waiting  wating block 三种线程状态演示
 */
public class MyTest1 {
    public static void main(String[] args) {
//      new Thread(()->{
//          System.out.println("111");
//      },"test1").start();

        new Thread(()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"STATUS_01").start();

        new Thread(()->{
            while(true){
                 synchronized (MyTest1.class){
                     try {
                         MyTest1.class.wait();
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
            }
        },"STATUS_02").start();

        new Thread(new BlockedDemo(),"BLOCKED-DEMO-01").start();
        new Thread(new BlockedDemo(),"BLOCKED-DEMO-02").start();
    }

    static  class BlockedDemo extends  Thread{
        @Override
        public void run() {
            synchronized (BlockedDemo.class){
                try {
                    BlockedDemo.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
