package demo1;

/**
 * Thread 创建线程
 */
public class MyTest1 extends Thread {

    public static void main(String[] args) {
        MyTest1 myTest1 = new MyTest1();
        myTest1.start();
    }
    @Override
    public void run(){
        System.out.println("用Thread 创建线程");
    }
}
