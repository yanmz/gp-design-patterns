package com.java.method;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MethodReferenceTest {
    public static void main(String[] args) {
        Student student1 = new Student("zhangsan",10);
        Student student2 = new Student("lisi",90);
        Student student3 = new Student("wangwu",20);
        Student student4 = new Student("zhaoliu",50);

        List<Student> students  = Arrays.asList(student1,student2,student3,student4);
        students.sort((a,b)->Student.compareStudentByScore(a,b));

        students.forEach(student -> System.out.println(student.getAcore()));

        System.out.println("-----------------------------");
        students.sort(Student::compareStudentByScore);//类名::静态方法名
        students.forEach(student -> System.out.println(student.getAcore()));
        System.out.println("-----------------------------");

        StudentCompartor studentCompartor = new StudentCompartor();

        students.sort((a,b)->studentCompartor.compareStudentByScore(a,b));
        students.forEach(student -> System.out.println(student.getAcore()));
        System.out.println("-----------------------------");
        students.sort(studentCompartor::compareStudentByScore);//对象名::实例方法名
        students.forEach(student -> System.out.println(student.getAcore()));

        List<String> list = Arrays.asList("qingdao","beijing","shanghai","changshao");
        Collections.sort(list,(city1,city2)->city1.compareToIgnoreCase(city2));
        list.forEach(s -> System.out.println(s));

        /**类名::实例方法名
         *(city1,city2)->city1.compareToIgnoreCase(city2)
         * lambda表达式的第一个参数作为调用者，第二个参数作为调用者所调用方法的形参
         */
        Collections.sort(list,String::compareToIgnoreCase);//类名::实例方法名（）
        list.forEach(System.out::println);
    }
}
