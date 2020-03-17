package com.java.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest13 {
    public static void main(String[] args) {
        Student student1= new Student("zhangsan",100,"20");
        Student student2= new Student("lisi",90,"14");
        Student student3= new Student("zhangsan",90,"20");
        Student student4= new Student("wangwu",80,"20");

        List<Student>  list = Arrays.asList(student1,student2,student3,student4);


        //根据名字分组
        Map<String, List<Student>> collect1 = list.stream().collect(Collectors.groupingBy(Student::getName));

        //根据年龄分组
        Map<String, List<Student>> collect2 = list.stream().collect(Collectors.groupingBy(Student::getAge));

        //根据年龄分组 并且得出总数
        Map<String, Long> collect3 = list.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));

        //根据年龄分组并且求其分数的平均值
        Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.averagingDouble(Student::getCore)));

        //分区
        list.stream().collect(Collectors.partitioningBy(student -> student.getCore()>=90));

    }
}
