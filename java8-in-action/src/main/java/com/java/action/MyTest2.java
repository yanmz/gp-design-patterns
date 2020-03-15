package com.java.action;



@FunctionalInterface
interface MyInterface{
    void test();

    @Override
    String toString();
}
public class MyTest2 {
    public void test(MyInterface  myInterface){
        System.out.println("1");
        myInterface.test();
        System.out.println("2");
    }

    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();
        myTest2.test(new MyInterface() {
            @Override
            public void test() {
                System.out.println("mytest");
            }
        });
        System.out.println("----------");

        myTest2.test(()->{
            System.out.println("3333333");
        });
        System.out.println("----------");

        myTest2.test(System.out::println);
    }
}
