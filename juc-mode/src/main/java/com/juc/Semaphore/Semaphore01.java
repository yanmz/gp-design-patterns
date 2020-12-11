package com.juc.Semaphore;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 信号灯 令牌
 * 用作限流
 */
public class Semaphore01 {
    public static void main(String[] args) {


            String str = "2222222222,3333333333,4444444444,5555555555";
            String[] split = str.split(",");
             Arrays.asList(split).stream().forEach(System.out::println);

//        Semaphore semaphore = new Semaphore(5);
//        for (int i = 0; i < 10; i++) {
//            new car(semaphore, i).start();
//        }
    }

    static class car extends Thread {
        private Semaphore semaphore;
        private int num;

        public car(Semaphore semaphore, int num) {
            this.semaphore = semaphore;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("抢占令牌" + num);
            semaphore.release();
        }
    }
}
