package com.java.action;

import java.util.function.Function;

public class FunctionTest1 {
    public static void main(String[] args) {
        System.out.println(compute(5,value->value*5));
    }

    public static  int compute(int a, Function<Integer,Integer> function){
        return function.apply(a);
    }
}
