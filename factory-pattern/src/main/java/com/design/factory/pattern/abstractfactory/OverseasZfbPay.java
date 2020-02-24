package com.design.factory.pattern.abstractfactory;

public class OverseasZfbPay implements ForeignPay {
    public void payWay() {
        System.out.println("境外支付宝支付。。。");
    }
}
