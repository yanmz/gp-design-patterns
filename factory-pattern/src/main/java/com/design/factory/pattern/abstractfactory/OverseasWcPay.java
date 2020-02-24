package com.design.factory.pattern.abstractfactory;

public class OverseasWcPay implements ForeignPay {
    public void payWay() {
        System.out.println("境外微信支付。。。");
    }
}
