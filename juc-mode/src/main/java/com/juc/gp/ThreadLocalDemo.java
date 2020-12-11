package com.juc.gp;


public class ThreadLocalDemo {

    //    private static int num=0;
    static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0; //初始化一个值
        }
    };
    static ThreadLocal<Integer> local1 = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0; //初始化一个值
        }
    };

    public static void main(String[] args) {
        Thread[] thread = new Thread[5];
        for (int i = 0; i < 5; i++) {
            thread[i] = new Thread(() -> {
                local1.set(10);
                int num = local.get(); //获得的值都是0
                local.set(num += 5); //设置到local中  thread[0] ->thread[1] ->
                System.out.println(Thread.currentThread().getName() + "-" + num);
                local.remove();
            });
        }
        for (int i = 0; i < 5; i++) {
            thread[i].start();
        }
    }
}
