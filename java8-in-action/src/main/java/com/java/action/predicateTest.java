package com.java.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class predicateTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        predicateTest predicateTest= new predicateTest();
        predicateTest.conditionFilter(list,value->value%2==0);
        System.out.println("---------------------------");
        predicateTest.conditionFilter(list,value->value%2!=0);
        System.out.println("----------------------------");
        predicateTest.conditionFilter(list,value->value>5);
        System.out.println("------------------------------");
        predicateTest.conditionFilter(list,value->value<3);
        System.out.println("-------------------------------");

        predicateTest.conditionFilter(list,value->true);
        System.out.println("----------------------------");
        predicateTest.conditionFilter(list,value->false);
        System.out.println("----------------------------");
        predicateTest.conditionFilter2(list,value->value>5,value->value%2==0);
        System.out.println("-----------------------------");
        boolean test = predicateTest.isEqual(new Date()).test(new Date());
        System.out.println(test);


    }

    public  void conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        for (Integer i:list){
            if(predicate.test(i)){
                System.out.println(i);
            }
        }
    }

    public void conditionFilter2(List<Integer> list,Predicate<Integer> predicate1,Predicate<Integer> predicate2){
        for (Integer i:list) {
            if(predicate1.and(predicate2).test(i)){
                System.out.println(i);
            }
        }
    }

    public Predicate<Date> isEqual(Object object){
       return Predicate.isEqual(object);
    }
}
