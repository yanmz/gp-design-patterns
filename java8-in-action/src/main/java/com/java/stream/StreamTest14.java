package com.java.stream;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yanmz
 *
 * flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所
 * 有使用map(Arrays::stream)时生成的单个流都被合并起来.
 *
 * 一言以蔽之，flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接
 * 起来成为一个流。
 */
public class StreamTest14 {
    public static void main(String[] args) {
        //需求：给定单词列表 ["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"]
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths);
        System.out.println("=================================");

        List<String> words1 = Arrays.asList("Hello", "World");
        List<String[]> collect = words1.stream()
                .map(w -> w.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("=================================");

        List<String> words2 = Arrays.asList("Hello", "World");
        List<Stream<String>> collect1 = words2.stream()
                .map(w -> w.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect1);

        System.out.println("=================================");
        List<String> words3 = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters =
                words3.stream()
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(uniqueCharacters);

=======
import java.util.*;

public class StreamTest14 {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 3, 4, 6, 0 ,4 ,8, 7 ,10, -3);
        list1.stream().filter(item->(item>3)).forEach(System.out::println);
>>>>>>> origin/master
    }
}
