package com.java.action;

import java.util.function.Supplier;

public class StudayTest {
    public static void main(String[] args) {
        Supplier<Studay> studaySupplier = ()-> new Studay();
        System.out.println(studaySupplier.get().getName());
        System.out.println("--------------------");
        Supplier<Studay> studaySupplier1 = Studay::new;
        System.out.println(studaySupplier.get().getAge());
    }
}
