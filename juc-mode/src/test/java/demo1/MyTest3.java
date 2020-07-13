package demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTest3 implements Runnable {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new MyTest3());
        executorService.shutdown();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("ExecutorService创建执行线程" + " " + i);
        }
    }
}
