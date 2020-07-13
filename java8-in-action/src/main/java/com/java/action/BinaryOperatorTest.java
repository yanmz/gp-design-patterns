package com.java.action;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorTest {
    public static void main(String[] args) {
        Integer binaryOperator1 = BinaryOperatorTest.getBinaryOperator(5, 6, (value, values) -> value * values);
        Integer binaryOperator2 = BinaryOperatorTest.getBinaryOperator(5, 6, (value, values) -> value - values);
        System.out.println(binaryOperator1);
        System.out.println(binaryOperator2);

        String aShort = BinaryOperatorTest.getShort("sdad", "hello", Comparator.comparingInt(String::length));
        System.out.println(aShort);
    }

    public static Integer getBinaryOperator(Integer a, Integer b, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(a, b);
    }

    public static String getShort(String a, String b, Comparator<String> comparator) {

        return BinaryOperator.maxBy(comparator).apply(a, b);
    }
}
