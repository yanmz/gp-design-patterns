package com.juc.cyclibarrier;

import java.util.concurrent.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author admin
 */
public class CycliBarrierTest {
    public static void main(String[] args) {
        int count = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
//        ExecutorService pool = Executors.newFixedThreadPool(count);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(10, count, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024),threadFactory,new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0;i<count; i++)
            pool.execute(new CycliBarrierTest().new Task(cyclicBarrier,i));
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   class Task implements  Runnable{

       private  CyclicBarrier cyclicBarrier;
       private int count;

       public Task(CyclicBarrier cyclicBarrier,int i){
           this.cyclicBarrier = cyclicBarrier;
           this.count = i;
       }

       @Override
       public void run() {
           try {
               cyclicBarrier.await();
               System.out.println("执行第 " + count);
           } catch (Exception e) {
              e.printStackTrace();
           }
       }
   }
 }
