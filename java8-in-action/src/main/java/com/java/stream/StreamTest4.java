package com.java.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest4 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "word", "hello word");

//        String[] stringArray = stream.toArray(value -> new String[value]);

        String[] StringArray1 = stream.toArray(String[]::new);
        Arrays.asList(stream).forEach(System.out::println);
        System.out.println("----------------------------------------------");
        List<String> list = stream.collect(Collectors.toList());

        List<String> list1 = stream.collect(() -> new ArrayList(), (a, b) -> a.add(b), (c, d) -> c.addAll(d));

        List<String> list2 = stream.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

        list.forEach(System.out::println);
        System.out.println("----------------------------------------------");

        ArrayList<String> collect = stream.collect(Collectors.toCollection(ArrayList::new));
        collect.forEach(System.out::println);
        System.out.println("----------------------------------------------");
        TreeSet<String> collect1 = stream.collect(Collectors.toCollection(TreeSet::new));
        collect1.forEach(System.out::println);
        System.out.println("----------------------------------------------");
        String collect2 = stream.collect(Collectors.joining());
        System.out.println(collect2);
    }
}
