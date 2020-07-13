package demo1;

public class MyTest6 extends Thread {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            System.out.println("B");
        });

        System.out.println("A");

        t.start();
    }
}
