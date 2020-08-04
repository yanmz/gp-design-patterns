package com.design.factory.pattern.abstractfactory;

/**
 *
 */
public class ZfbPayFactory extends PayFactory {

    @Override
    public DomesticPay domesticPay() {
        return new ZfbPay();
    }

    @Override
    public ForeignPay foreignPay() {
        return new OverseasZfbPay();
    }
}
