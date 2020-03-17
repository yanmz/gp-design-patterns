package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest12 {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("Hi","Helli","你好");
        List<String> list2 = Arrays.asList("zhangsan","lisi","wangwu");
        List<String> collect = list1.stream().flatMap(item -> list2.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
}
