package com.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 返回资源的加载路径
 */
public class MyTest14 {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String resources = "com/classloader/MyTest13.class";
        Enumeration<URL> resource = classLoader.getResources(resources);
        while (resource.hasMoreElements()) {
            URL url = resource.nextElement();
            System.out.println(url);
        }

    }
}
