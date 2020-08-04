package demo1;

import java.util.Collections;
import java.util.stream.Collectors;

public class MyTest7 {
    private static int i = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            i = 30;
        });
        thread.start();
        thread.join();
        System.out.println(i);
    }
}
