package com.juc.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CycliBarrierDemo extends Thread{
 @Override
 public void run() {
 System.out.println("开始进行数 据分析");
 }
 public static void main(String[] args) {
 CyclicBarrier cycliBarrier=new CyclicBarrier(3,new CycliBarrierDemo());
 new Thread(new DataImportThread(cycliBarrier,"file 1")).start();
 new Thread(new DataImportThread(cycliBarrier,"file 2")).start();
 new Thread(new DataImportThread(cycliBarrier,"file 3")).start();
 } }