package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 创建流的几种方式
 */
public class StreamTest1 {
    public static void main(String[] args) {
        Stream stream = Stream.of("hello", "word", "hello word");
        String[] myArray = new String[]{"hello", "word", "hello word"};
        Stream stream1 = Stream.of(myArray);
        Stream stream2 = Arrays.stream(myArray);

        List<String> list = Arrays.asList(myArray);
        Stream stream3 = list.stream();
    }
}
