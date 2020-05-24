package com.juc.gp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打破循环等待，线程 T1 等待线程 T2 占有的资源，线程 T2 等待线程 T1 占有的资源，就是循环等待
 */
public class MyTest8_03 implements  Runnable {
    private Account fromAccount; //转出账户
    private Account toAccount; //转入账户
    private int amount;
    private Lock fromLock = new ReentrantLock();
    private Lock toLock = new ReentrantLock();

    public MyTest8_03(Account fromAccount, Account toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }


    @Override
    public void run() {
        Account left=null;
        Account right=null;
        if(fromAccount.hashCode()>toAccount.hashCode()){
            left=toAccount;
            right=fromAccount;
        }
        while (true) {
            synchronized (left) { //返回true和false
                synchronized (right) {//返回true和false
                    if (fromAccount.getBalance() >= amount) {
                        fromAccount.debit(amount);
                        toAccount.debit(amount);
                    }
                }
            }
        //转出账户的余额
        System.out.println(Thread.currentThread().getName() + " " + fromAccount.getAccountName() + "->" + fromAccount.getBalance());
        //转入账户的余额
        System.out.println(Thread.currentThread().getName() + " " + toAccount.getAccountName() + "->" + toAccount.getBalance());
    }
}
    public static void main(String[] args) {
        Account fromAccount=new Account("Mic",100000);
        Account toAccount=new Account("花花",300000);
        Allocator allocator = new Allocator();
        Thread a =new Thread(new MyTest8_03(fromAccount,toAccount,1));
        Thread b=new Thread(new MyTest8_03(toAccount,fromAccount,30));

        a.start();
        b.start();
    }
}
