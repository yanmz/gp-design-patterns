package com.juc.gp;

public class MyTest2 implements Runnable {

    int count=0;
    @Override
    public void run() {
      while (!Thread.currentThread().isInterrupted()){
          System.out.println(count++);
      }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyTest2());
        thread.start();
        Thread.sleep(200);
        thread.interrupt();
    }
}
