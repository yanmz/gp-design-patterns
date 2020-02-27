package com.classloader;

public class MyTest10 {
    static {
        System.out.println("MyTest10 static block...");
    }
    public static void main(String[] args) {
        Parent10 parent10;
        System.out.println("--------------");
        parent10 = new Parent10();
        System.out.println("--------------");
        System.out.println(Parent10.a);
        System.out.println("--------------");
        System.out.println(Child10.b);

    }
}

class Parent10{
    public static int a= 1;
    static {
        System.out.println("Parent10 static block...");
    }
}

class Child10{
    public  static int b= 2;
    static {
        System.out.println("Child10 static block...");
    }
}