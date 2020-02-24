package com.design.factory.pattern.factorymethod;

public class WcPayFactory implements FactoryMethodPay {
    public Pay pay() {
        return new WcPay();
    }
}
