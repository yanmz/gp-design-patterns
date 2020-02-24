package com.design.factory.pattern.factorymethod;

public class JdPayFactory implements FactoryMethodPay {
    public Pay pay() {
        return new JdPay();
    }
}
