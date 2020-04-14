package com.java.stream2;

import java.util.*;
import java.util.stream.Collectors;

public class StudentTest {
    public static void main(String[] args) {
        Student student1 = new Student("zhangsan",20);
        Student student2 = new Student("lisi",30);
        Student student3 = new Student("wangwu",23);
        Student student4 = new Student("zhaoliu",53);
        List<Student> students = Arrays.asList(student1, student2, student3, student4);
        //遍历元素
        List<Student> list = students.stream().collect(Collectors.toList());
        list.forEach(System.out::println);
        //元素总数
        System.out.println(students.stream().collect(Collectors.counting()));
        System.out.println(students.stream().count());

        System.out.println("-------------------------------------");
        //最小年龄
        students.stream().collect(Collectors.minBy(Comparator.comparingInt(Student::getAge))).ifPresent(System.out::println);
        //最大年龄
        students.stream().collect(Collectors.maxBy(Comparator.comparingInt(Student::getAge))).ifPresent(System.out::println);

        //平均年龄
        System.out.println(students.stream().collect(Collectors.averagingInt(Student::getAge)));

        //年龄总和
        System.out.println(students.stream().collect(Collectors.summingInt(Student::getAge)));

        System.out.println(students.stream().collect(Collectors.summarizingInt(Student::getAge)));

        System.out.println("-------------------------------------");
        //名字字符串拼接
        System.out.println(students.stream().map(Student::getName).collect(Collectors.joining()));
        System.out.println(students.stream().map(Student::getName).collect(Collectors.joining(",")));
        System.out.println("-------------------------------------");

        //根据年龄和名字分组
        Map<Integer, Map<String, List<Student>>> collect = students.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.groupingBy(Student::getName)));
        System.out.println(collect);

        System.out.println("-------------------------------------");
        //根据年龄分区
        Map<Boolean, List<Student>> collect1 = students.stream().collect(Collectors.partitioningBy(item -> item.getAge() > 20));
        System.out.println(collect1);
        System.out.println("-------------------------------------");

        //两层分区
        Map<Boolean, Map<Boolean, List<Student>>> collect2 = students.stream().collect(Collectors.partitioningBy(item -> item.getAge() > 20, Collectors.partitioningBy(it -> it.getAge() > 30)));
        System.out.println(collect2);
        System.out.println("-------------------------------------");
        //分区并得出总数
        Map<Boolean, Long> collect3 = students.stream().collect(Collectors.partitioningBy(student -> student.getAge() > 20, Collectors.counting()));
        System.out.println(collect3);

        System.out.println("-------------------------------------");
        Map<String, Student> collect4 = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(Student::getAge)), Optional::get)));
        System.out.println(collect4);


    }
}
