package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CountTool {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    volatile int i = 0;


    public void incre() throws Exception {
        lock.lock();
        if (i > 0) {
            condition.await();
        }
        i++;
        System.out.println("线程：" + Thread.currentThread().getName() + ",i:" + i);
        condition.signalAll();
        lock.unlock();
    }

    public void decre() throws Exception {
        lock.lock();
        if (i < 0) {
            condition.await();
        }
        i--;
        System.out.println("线程：" + Thread.currentThread().getName() + ",i:" + i);
        condition.signalAll();
        lock.unlock();
    }
}
