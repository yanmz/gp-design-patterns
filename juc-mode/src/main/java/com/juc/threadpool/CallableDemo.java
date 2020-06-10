package com.juc.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        //Thread.sleep(3000);//阻塞案例演示
        return "hello world";
    }

    public static void main(String[] args) throws ExecutionException,
            InterruptedException {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask futureTask = new FutureTask(callableDemo);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}