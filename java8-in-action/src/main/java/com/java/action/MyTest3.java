package com.java.action;

import java.util.Arrays;
import java.util.List;

public class MyTest3 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "word", "uyu");
        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));
        System.out.println("----------------------------------");
        list.stream().map(String::toUpperCase).forEach(item -> System.out.println(item));
    }
}
