package com.classloader;

public class MyTest8 {
    public static void main(String[] args) {
        System.out.println(FianlTest.str);
    }
}

class FianlTest {
    public static final int str = 1;

    static {
        System.out.println("FinalTest static block...");
    }
}
