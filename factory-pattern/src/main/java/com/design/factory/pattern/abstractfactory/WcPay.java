package com.design.factory.pattern.abstractfactory;

public class WcPay implements DomesticPay {
    public void payWay() {
        System.out.println("微信支付。。。");
    }
}
