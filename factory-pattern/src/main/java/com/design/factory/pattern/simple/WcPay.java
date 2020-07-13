package com.design.factory.pattern.simple;

public class WcPay extends Pay {
    @Override
    public void payWay() {
        System.out.println("微信支付。。。");
    }
}
