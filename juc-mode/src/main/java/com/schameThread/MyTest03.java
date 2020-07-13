package com.schameThread;

public class MyTest03 {
    private int money;
    private String name;

    public MyTest03(int money, String name) {
        this.money = money;
        this.name = name;
    }

    public synchronized void deposit(int m) {
        money += m;
    }

    public synchronized boolean withdraw(int m) {
        if (money > m) {
            money -= m;
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + "--" + money + "--" + this.getName();
    }
}
