package com.java.stream;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamTest16 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        System.out.println("========================");

        Stream<String> emptyStream = Stream.empty();

        System.out.println("========================");
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
        System.out.println("========================");

//        long uniqueWords = 0;
//        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
//            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
//                    .distinct()
//                    .count();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        System.out.println("============================");
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }
}
