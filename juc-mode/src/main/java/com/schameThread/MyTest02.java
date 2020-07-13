package com.schameThread;

public class MyTest02 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("NICE+");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
