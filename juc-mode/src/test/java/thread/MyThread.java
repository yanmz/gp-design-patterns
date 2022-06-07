package thread;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/11/18 11:23
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            System.out.println("i=" + (i + 1));
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        //Thread.sleep(10);
        myThread.interrupt();
        System.out.println("zzzzzzzzzz");
    }
}

