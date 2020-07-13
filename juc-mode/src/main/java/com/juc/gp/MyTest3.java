package com.juc.gp;

public class MyTest3 {

    public synchronized static void inc() {//类锁
    }

    public synchronized void getcount() {//对象锁
    }

    public void getName() {
        synchronized (this) {//括号里对象决定是类锁还是对象锁
        }
    }

    public static void main(String[] args) {
    }
}
