package com.design.factory.pattern.simple;

public class SimpleFactory {

    public static Pay pay(Class<? extends Pay> pay) throws Exception {
        return pay.newInstance();
    }
}
