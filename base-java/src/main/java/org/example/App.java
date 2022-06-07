package org.example;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "9", "22", "33", "3");
        for (String str : list) {
            System.out.println(str);
        }

        //hashcode相同  equals不一定相等
        //equals相等 hashcode一定相同
        Integer a = 97;
        String b = "a";
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));


        Person person1 = new Person();
        person1.setName("1");
        person1.setAge(18);


        Person person2 = new Person();
        person2.setName("1");
        person2.setAge(18);
        System.out.println("-----------------------------------------");
        System.out.println(person2.equals(person1));
        System.out.println(person1.hashCode() == person2.hashCode());
        System.out.println("-----------------------------------------");
    }
}
