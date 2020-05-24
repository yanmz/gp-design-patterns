package com.juc.gp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 不可抢占，其他线程不能强行抢占线程 T1 占有的资源  使用ReentrantLock
 */
public class MyTest8_02 implements  Runnable {
    private Account fromAccount; //转出账户
    private Account toAccount; //转入账户
    private int amount;
    private Lock fromLock = new ReentrantLock();
    private Lock toLock = new ReentrantLock();

    public MyTest8_02(Account fromAccount, Account toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }


    @Override
    public void run() {
        while (true) {
            if (fromLock.tryLock()) {
                if (toLock.tryLock()) {
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
        Thread a =new Thread(new MyTest8_02(fromAccount,toAccount,1));
        Thread b=new Thread(new MyTest8_02(toAccount,fromAccount,30));

        a.start();
        b.start();
    }
}
