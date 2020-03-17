package com.java.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest10 {
    public static void main(String[] args) {
        List<String> list  = Arrays.asList("hello","world","hello world");
        //把第一个长度为5的筛选出来并打印长度
//        list.stream().mapToInt(value -> value.length()).filter(item->item==5).findFirst().ifPresent(System.out::println);

        list.stream().mapToInt(value -> {
           int length =  value.length();
           System.out.println(value);
           return length;
        }).filter(item->item==5).findFirst().ifPresent(System.out::println);

    }
}
