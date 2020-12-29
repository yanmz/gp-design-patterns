package com.java.action;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PersonTest {
    public static void main(String[] args) {
        Person person1 = new Person("zhangsan", 20);
        Person person2 = new Person("lisi", 40);
        Person person3 = new Person("wangwu", 60);
        PersonTest personTest = new PersonTest();
        List<Person> list = Arrays.asList(person1, person2, person3);
        List<Person> zhangsan = personTest.getPersonUsername("zhangsan", list);
        zhangsan.forEach(person -> System.out.println(person.getName()));

        System.out.println("--------------------------------");
        List<Person> personUsername1 = personTest.getPersonUsername1(20, list);
        personUsername1.forEach(person -> System.out.println(person.getAge()));

        System.out.println("--------------------------------");
        List<Person> personByAge = personTest.getPersonByAge(20, list);
        personByAge.forEach(person -> System.out.println(person.getAge()));

        System.out.println("--------------------------------");
        List<Person> personLists  = personTest.getPersonByAge2(20,list,(age,personList1)->{
            return personList1.stream().filter(person -> person.getAge()>age).collect(Collectors.toList());
        });
        personLists.forEach(person-> System.out.println(person.getAge()));

    }

    public List<Person> getPersonUsername(String name, List<Person> personList) {
        return personList.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
    }

    public List<Person> getPersonUsername1(int age, List<Person> personList) {
        return personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList());
    }

    public List<Person> getPersonByAge(Integer age, List<Person> personList) {
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (age1, personList1) -> {
            return personList1.stream().filter(person -> person.getAge() > age1).collect(Collectors.toList());
        };
        return biFunction.apply(age, personList);
    }

    public List<Person> getPersonByAge2(Integer age,List<Person> persons,BiFunction<Integer,List<Person>,List<Person>> biFunction){
        return  biFunction.apply(age,persons);
    }
}
