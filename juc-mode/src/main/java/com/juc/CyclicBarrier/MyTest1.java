package com.juc.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class MyTest1 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
            for (int i =0;i<10;i++){
              new Thread(new Car(cyclicBarrier,i)).start();
             }
    }

    static  class Car implements   Runnable{
        CyclicBarrier cyclicBarrier;
        int num;
        public Car(CyclicBarrier cyclicBarrier,int num){
            this.cyclicBarrier = cyclicBarrier;
            this.num = num;
        }
        @Override
        public void run() {
            try {
                System.out.println("栅栏----"+num);
                cyclicBarrier.await(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
