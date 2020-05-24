package com.juc.gp;

/**
 * 占有且等待，线程 T1 已经取得共享资源 X，在等待共享资源 Y 的时候，不释放共享资源 X
 */
public class MyTest8_01 implements  Runnable{
    private Account fromAccount; //转出账户
    private Account toAccount; //转入账户
    private int amount;
    private Allocator allocator;

    public MyTest8_01(Account fromAccount, Account toAccount, int amount,Allocator allocator) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.allocator = allocator;
    }


    @Override
    public void run() {
        while (true){
            if(allocator.apply(fromAccount,toAccount)) {
                try {
                    synchronized (fromAccount) {
                        synchronized (toAccount) {
                            if (fromAccount.getBalance() >= amount) {
                                fromAccount.debit(amount);
                                toAccount.debit(amount);
                            }
                        }
                    }
                }finally {
                    allocator.free(fromAccount,toAccount);
                }
            }
            //转出账户的余额
            System.out.println(Thread.currentThread().getName()+" "+fromAccount.getAccountName() + "->" + fromAccount.getBalance());
            //转入账户的余额
            System.out.println(Thread.currentThread().getName()+" "+toAccount.getAccountName() + "->" + toAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        Account fromAccount=new Account("Mic",100000);
        Account toAccount=new Account("花花",300000);
        Allocator allocator = new Allocator();
        Thread a =new Thread(new MyTest8_01(fromAccount,toAccount,1,allocator));
        Thread b=new Thread(new MyTest8_01(toAccount,fromAccount,30,allocator));

        a.start();
        b.start();
    }
}
