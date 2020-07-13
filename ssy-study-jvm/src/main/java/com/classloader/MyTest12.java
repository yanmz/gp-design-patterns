package com.classloader;

public class MyTest12 {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = loader.loadClass("com.classloader.CL");
        System.out.println(aClass);
        System.out.println("-----------------------");
        aClass = Class.forName("com.classloader.CL");
        System.out.println(aClass);
    }
}

class CL {
    static {
        System.out.println("CL");

    }
}
