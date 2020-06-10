package com.design.factory.singtelon;

public class Test {
    public static void main(String[] args) {
//        for(int i=0;i<1000;i++){
//            new Thread(()->{
//                System.out.println(Singleton.getInstance());
//            },"Thread"+i).start();
//        }
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.getInstance());
    }
}
