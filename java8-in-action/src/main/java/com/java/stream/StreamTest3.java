package com.java.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest3 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(list.stream().map(integer -> integer * 2).reduce(0, Integer::sum));
    }
}
