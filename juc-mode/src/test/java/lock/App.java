package lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 验证核心线程是否会被销毁
 */
public class App {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                2,
                10,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
         executor.allowCoreThreadTimeOut(true);
         for (int  i=0;i<2;i++){
             executor.execute(new Thread(()->{
                 System.out.println(Thread.currentThread().getName()+"  执行任务");
             }));
         }
    }
}
