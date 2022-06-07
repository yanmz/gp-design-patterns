package com.design.factory.singtelon;

/**
 * 饿汉
 */
public class HungerSingtelon {

    private HungerSingtelon() {
    }


    private static final HungerSingtelon HUNGERSINGTELON = new HungerSingtelon();


    public static HungerSingtelon getInstance() {
        return HUNGERSINGTELON;
    }
}
