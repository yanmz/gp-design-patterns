package com.java.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamTest9 {
    public static void main(String[] args) {
        List<String> list  = new ArrayList<>(5000000);
        for (int i=0;i<5000000;i++){
            list.add(UUID.randomUUID().toString());
        }
        System.out.println("开始排序");
        long startTime = System.nanoTime();

        list.parallelStream().sorted().count();//用并行流处理
        list.stream().sorted().count();//用串行流处理
        long endTime = System.nanoTime();
        long  milles = TimeUnit.NANOSECONDS.toMillis(endTime-startTime);
        System.out.println("耗时:"+milles);
    }
}
