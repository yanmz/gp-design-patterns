package com.classloader;

public class MyTest9 {

    static {
        System.out.println("MyTest9 static block...");
    }

    public static void main(String[] args) {
        System.out.println(Child.b);
    }
}

class Parent {
    public static int a = 3;

    static {
        System.out.println("Parent static block...");
    }
}

class Child extends Parent {
    public static int b = 4;

    static {
        System.out.println("Child static block...");
    }
}

