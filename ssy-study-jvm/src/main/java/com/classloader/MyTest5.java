package com.classloader;

import java.util.Random;

/**
 * 当一个接口初始化时，并不要求其父接口都完成了初始化
 * 只有在真正使用到父接口的时候(如引用接口定义的常量时)，才会初始化
 */

public class MyTest5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.b);
        System.out.println(MyParent5_1.thread);
    }
}
interface  MyParent5{
        public static int a  = new Random().nextInt(2);

        public static Thread thread = new Thread(){
            {
                System.out.println("MyParent5  invoked");
            }
        };
}

interface MyChild5 extends MyParent5{
    public  static int b = 5;
}

interface  MyGrandpa5_1{
    public static Thread thread = new Thread(){
        {
            System.out.println("MyGrandpa5_1  invoked");
        }
    };
}

interface  MyParent5_1 extends  MyGrandpa5_1{
    public static Thread thread = new Thread(){
        {
            System.out.println("MyParent5_1  invoked");
        }
    };
}