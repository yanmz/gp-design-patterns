package com.java.stream;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

public class StreamTest6 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
//        stream.findFirst().ifPresent(System.out::println);
//        iterate 表示从1开始一直迭代
//        Stream.iterate(1,item->item+2).limit(10).forEach(System.out::println);

        Stream.iterate(1,item->item+2).limit(10).filter(value->value>2).mapToInt(i->i*2).skip(2).limit(2).min().ifPresent(System.out::println);

        Stream<Integer> limit = Stream.iterate(1, item -> item + 2).limit(10);
        IntSummaryStatistics summaryStatistics = limit.filter(i -> i > 2).mapToInt(item -> item * 2).skip(2).limit(2).summaryStatistics();
        System.out.println(summaryStatistics.getMax());
        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getCount());
    }
}
