package com.java.stream2;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyTest2 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            list1.add("a");
            list1.add("b");
            list1.add("c");
            list1.add("d");
            list1.parallelStream().forEach(list->list2.add(list));
            System.out.println(list2.size());
        }
    }

}
