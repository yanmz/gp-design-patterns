package com.design.factory.pattern.abstractfactory;

public class WcPayFactory extends PayFactory {
    @Override
    public DomesticPay domesticPay() {
        return new WcPay();
    }

    @Override
    public ForeignPay foreignPay() {
        return new OverseasWcPay();
    }
}
