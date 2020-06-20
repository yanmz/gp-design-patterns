package demo1;

import java.util.concurrent.*;

public class MyTest4 implements Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        FutureTask futureTask = new  FutureTask(new MyTest4());

        futureTask.run();
        Object o = futureTask.get();

        System.out.println(o);
//        Future future = executorService.submit(new MyTest4());
//        try {
//            System.out.println(future.get());
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public Object call() throws Exception {
        return "www.baidu.com";
    }
}
