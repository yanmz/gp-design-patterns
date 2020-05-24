package com.juc.gp;

public class MyTest6 {
    public  static  Boolean stop = false;
    final  int i=0;
    public static  void count(){
        //i为int 类型存在可见性 为Integer不存在可见性
        while(!stop){

        }
    }
    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(()->{
//            int i=0;
//            while(!stop){
//                i++;
//            }
//        });
        Thread thread = new Thread(()->{
            count();
        },"Test-1");
        thread.start();
         Thread.sleep(1000);
         stop=true;
    }
}
