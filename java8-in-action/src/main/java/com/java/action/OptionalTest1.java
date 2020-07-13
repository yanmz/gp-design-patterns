package com.java.action;

import java.util.Optional;

public class OptionalTest1 {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("hello");

        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
        optional.ifPresent(item -> System.out.println(item));

        System.out.println("----------------------");
        Optional<String> word = Optional.ofNullable("word");
        System.out.println(word.get());

        System.out.println("----------------------");
        String str = null;
        Optional<String> word1 = Optional.ofNullable(str);
        System.out.println(word1.getClass());

    }
}
