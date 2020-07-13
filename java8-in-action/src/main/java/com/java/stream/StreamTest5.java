package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest5 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "word", "hello word");
        list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("--------------------");

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream().map(value -> value * value).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("--------------------");

        Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        listStream.flatMap(value -> value.stream()).map(i -> i * i).forEach(System.out::println);


    }
}
