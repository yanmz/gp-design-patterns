package com.java.action;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MyTest1 {
    public static void main(String[] args) {
        List<Integer> list  = Arrays.asList(1,2,3,4,5,6,7);

        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("--"+integer);
            }
        });
        System.out.println("------------------------");
        list.forEach(integer -> System.out.println(integer));
    }
}
