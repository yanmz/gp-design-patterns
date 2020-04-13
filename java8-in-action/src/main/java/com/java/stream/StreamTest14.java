package com.java.stream;

import java.util.*;

public class StreamTest14 {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 3, 4, 6, 0 ,4 ,8, 7 ,10, -3);
        list1.stream().filter(item->(item>3)).forEach(System.out::println);
    }
}
