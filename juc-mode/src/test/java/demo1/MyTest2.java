package demo1;

/**
 * Runable 实现线程创建
 */
public class MyTest2 implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyTest2());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Runable创建线程");
    }
}
