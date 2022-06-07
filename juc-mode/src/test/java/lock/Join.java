package lock;

import java.util.concurrent.TimeUnit;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/10/3 21:02
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {

        Thread t = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(t), String.valueOf(i));
            thread.start();
            t = thread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + "  Terminate.");
    }

    static class Domino implements Runnable {

        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " Terminate. ");
        }
    }
}
