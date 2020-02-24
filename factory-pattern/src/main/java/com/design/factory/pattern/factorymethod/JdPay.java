package com.design.factory.pattern.factorymethod;

public class JdPay extends Pay {
    @Override
    public void payWay() {
        System.out.println("JD 支付。。。");
    }
}
