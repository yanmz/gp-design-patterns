package com.juc.gp;

public class MyTest2 {
    private     int  count;
    public    void inc(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }
    public static void main(String[] args) throws InterruptedException {
        MyTest2 myTest2 = new MyTest2();
        for(int  i=0;i<1000;i++){
            new Thread(()->{
                myTest2.inc();
            },"t1").start();
        }
        Thread.sleep(1000);
        System.out.println(myTest2.count);
    }
}
