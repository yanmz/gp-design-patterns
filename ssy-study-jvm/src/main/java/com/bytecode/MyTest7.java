package com.bytecode;

import java.util.Date;

public class MyTest7 {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.test("hello");

        Animal dog = new Dog();
        dog.test(new Date());
    }
}

class Animal {
    public void test(String str) {
        System.out.println("Animal str");
    }

    public void test(Date date) {
        System.out.println("Animal date");
    }
}

class Dog extends Animal {
    @Override
    public void test(String str) {
        System.out.println("Dog  str");
    }

    @Override
    public void test(Date date) {
        System.out.println("Dog date");
    }
}