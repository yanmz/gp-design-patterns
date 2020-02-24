package com.design.factory.pattern.abstractfactory;

public class OverseasYlPay implements ForeignPay {
    public void payWay() {
        System.out.println("境外JD支付。。。");
    }
}
