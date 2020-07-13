package com.juc.gp;

/**
 * 互斥，共享资源 X 和 Y 只能被一个线程占用；
 */
public class MyTest8 implements Runnable {
    private Account fromAccount; //转出账户
    private Account toAccount; //转入账户
    private int amount;

    public MyTest8(Account fromAccount, Account toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
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
        Account fromAccount = new Account("Mic", 100000);
        Account toAccount = new Account("花花", 300000);
        Thread a = new Thread(new MyTest8(fromAccount, toAccount, 1));
        Thread b = new Thread(new MyTest8(toAccount, fromAccount, 30));

        a.start();
        b.start();
    }
}
