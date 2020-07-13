package com.classloader;

public class MyTest11 {
    public static void main(String[] args) {
        System.out.println(Child11.a);
        Child11.doSomething();
    }
}

class Parent11 {
    public static int a = 1;

    static {
        System.out.println("Parent11 static block...");
    }

    public static void doSomething() {
        System.out.println("Parent static doSomething...");
    }
}

class Child11 extends Parent11 {
    public static int b = 2;

    static {
        System.out.println("Child11 static block...");
    }
}
