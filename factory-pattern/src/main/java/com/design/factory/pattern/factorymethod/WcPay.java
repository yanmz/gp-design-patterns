package com.design.factory.pattern.factorymethod;

public class WcPay  extends Pay {
    @Override
    public void payWay() {
        System.out.println("微信支付。。。");
    }
}
