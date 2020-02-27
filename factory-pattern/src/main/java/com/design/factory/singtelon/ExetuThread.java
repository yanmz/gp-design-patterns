package com.design.factory.singtelon;

public class ExetuThread implements Runnable {
    @Override
    public void run() {
        Object singtelon = contanierSingleton.getSingtelon("com.design.factory.singtelon.contanierSingleton");
        System.out.println(singtelon);
        Object singtelon1 = contanierSingleton.getSingtelon("com.design.factory.singtelon.contanierSingleton");
        System.out.println(singtelon1);
    }
}
