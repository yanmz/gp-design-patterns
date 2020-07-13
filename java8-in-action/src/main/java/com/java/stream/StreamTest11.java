package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest11 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");

        List<String[]> collect1 = list.stream().map(item -> item.split(" ")).distinct().collect(Collectors.toList());
        collect1.forEach(item -> Arrays.asList(item).forEach(System.out::println));

        System.out.println("-----------------------------------------");
        //演示flatMap的作用 相当于打平原来的元素
        List<String> collect = list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
