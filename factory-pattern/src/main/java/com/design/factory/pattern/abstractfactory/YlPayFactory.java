package com.design.factory.pattern.abstractfactory;

public class YlPayFactory extends PayFactory {
    @Override
    public DomesticPay domesticPay() {
        return new YlPay();
    }

    @Override
    public ForeignPay foreignPay() {
        return new OverseasYlPay();
    }
}
