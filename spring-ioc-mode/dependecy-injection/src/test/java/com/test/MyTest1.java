package com.test;

import java.lang.reflect.Method;

public class MyTest1 {
    public static void main(String[] args) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.test.MyTest2");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods =  clazz.getDeclaredMethods();
        for(Method m:methods){
            System.out.println(m.getName());
        }

    }
}
