package com.java.method;

import java.util.function.Function;
import java.util.function.Supplier;

public class constructionTest {

    public static void main(String[] args) {

        constructionTest construction = new constructionTest();
        System.out.println(construction.getString(String::new));

        System.out.println(construction.getString2("hello",String::new));
    }
    public String getString(Supplier<String> supplier){
        return supplier.get()+"test";
    }

    public String getString2(String str, Function<String,String> function){
        return function.apply(str);
    }
}
