package com.design.factory.decorator;

public class Test {
    public static void main(String[] args) {
        System.out.println("-------未登录-------");
        GPOrdinaryLogin gpOrdinaryLogin = new GPOrdinaryLogin();
        System.out.println(gpOrdinaryLogin.column());
        System.out.println("-------登录-------");
        GPDecorate gpDecorate = new Gplecturer(gpOrdinaryLogin);
        System.out.println(gpDecorate.add());


    }
}
