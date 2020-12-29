package com.java.stream2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Demo {
 
    public static void main(String[] args) {
        CuratorFramework curatorFramework=null;
        curatorFramework= CuratorFrameworkFactory.builder()
//                 .connectString(ZkConfig.ZK_CONNECT_STR)
//                 .sessionTimeoutMs(ZkConfig.ZK_SESSION_TIMEOUT)
                 .retryPolicy(new ExponentialBackoffRetry(1000,10)). build();
        curatorFramework.start();
 
        final InterProcessMutex lock=new InterProcessMutex(curatorFramework ,"/locks");
        for(int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"->尝试获取锁");
                try {
                    lock.acquire();
                    System.out.println(Thread.currentThread().getName()+"->获得锁成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(4000);
                    lock.release();
                    System.out.println(Thread.currentThread().getName()+"->释放锁成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"t"+i).start();         } 
 
    } } 