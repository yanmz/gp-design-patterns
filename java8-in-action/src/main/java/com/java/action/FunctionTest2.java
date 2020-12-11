package com.java.action;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionTest2 {
    public static void main(String[] args) {

        System.out.println(compute(2, value -> value + value, value -> value * value));//8

        System.out.println(compute2(2, value -> value + value, value -> value * value));//16

        System.out.println(compute3(2, 4, (value1, value2) -> value1 * value2));


    }

    public static int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(a);
    }

    public static int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a);
    }

    public static int compute3(int a, int b, BiFunction<Integer, Integer, Integer> function1) {
        return function1.apply(a, b);
    }

    public static int compute4(int a, int b, BiFunction<Integer, Integer, Integer> biFunction, Function<Integer, Integer> function) {
        return biFunction.andThen(function).apply(a, b);
    }

}
