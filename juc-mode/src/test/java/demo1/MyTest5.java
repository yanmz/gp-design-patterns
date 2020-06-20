package demo1;

import java.util.Date;

public class MyTest5{
    public static void main(String[] args) {
        TimeThread timeThread = new TimeThread();//创建了一个线程
        timeThread.start();//调用start()方法，使timeThread线程就绪
        try {
            Thread.sleep(10000);//为了看出interrupt()方法的效果，停顿10s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeThread.interrupt();//中断timeThread线程，在timeThread线程阻塞处爆发异常并处理该异常，处理完后程序向下执行，打印输出date
    }
}

class TimeThread extends Thread{

    @Override
    public void run() {
        while (true) {
            Date date = new Date();
            try {
                sleep(60000);//能直接使用sleep()方法，因为TimeThread继承自Thread类，继承了该类的方法
                System.out.println(date);//不执行，因为调用interrupt方法爆发异常后，直接由e.printStackTrace();处理异常，所以try里的语句不再执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(date);//处理完异常后，打印输出该句话
        }
    }
}
