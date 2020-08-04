package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTest1 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        A a = new A();
        a.str(lock, 11);
    }
}

class A {
    public void str(Lock lock, int i) {
        lock.lock();
        i++;
        if (i > 10) {
            return;
        }
        lock.unlock();
    }
}
