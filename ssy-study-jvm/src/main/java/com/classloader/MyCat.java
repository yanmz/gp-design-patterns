package com.classloader;

public class MyCat {

    public MyCat() {
        System.out.println("MyCat  is loaded  by:" + this.getClass().getClassLoader());
        System.out.println("from mycat: " + MySample.class);
    }
}
