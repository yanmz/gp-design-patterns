package com.java.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StreamTest9 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5000000);
        for (int i = 0; i < 5000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        System.out.println("开始排序");
        long startTime = System.nanoTime();

//        for ( String str :list) {
//            System.out.println(str);
//        }

        list.stream().collect(Collectors.toList()).forEach(System.out::println);

//        list.parallelStream().sorted().count();//用并行流处理
//        list.stream().sorted().count();//用串行流处理
        long endTime = System.nanoTime();
        long milles = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("耗时:" + milles);
    }
}
