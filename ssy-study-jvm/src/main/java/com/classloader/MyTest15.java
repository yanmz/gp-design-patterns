package com.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MyTest15 {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String resourceName = "com/classloader/MyTest13.class";
        Enumeration<URL> url = classLoader.getResources(resourceName);
        while (url.hasMoreElements()){
            URL url1 = url.nextElement();
            System.out.println(url1);
        }
    }
}
