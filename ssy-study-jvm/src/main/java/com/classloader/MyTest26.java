package com.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 线程上下文类加载器的一版使用模式(获取--使用--还原)
 * <p>
 * ClassLoader classloader  = Thread.currentThread().getContextLoader();
 * try{
 * Thread.currentThread.setContextClassLoader(tragetTcll)
 * myMethod();
 * }finally{
 * Thread.currentThread().setConetxtClassLoader();
 * <p>
 * }
 * <p>
 * myMethod里面则调用了Thread.currentThread().getContextLoader() 获取当前线程的上下文类加载器做某些事情。
 * 如果一个类由类加载器A加载，那么这个类的依赖类也是有相同类加载器加载(如果该依赖类未被加载过)
 * ContextClassLoader的作用就是为了破坏java的类加载委托机制
 * <p>
 * 当高层提供了统一的接口让底层去实现，同时又要在高层加载（或实例化）底层的类时，就必须要通过线程上下文类加载器来帮助高层的ClassLoad而找到并加载该类
 */
public class MyTest26 {
    public static void main(String[] args) {
        //将当前系统类加载器设置成MyTest26 加载器的父加载器
        Thread.currentThread().setContextClassLoader(MyTest26.class.getClassLoader().getParent());

        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver: " + driver.getClass() + ",loader: " + driver.getClass().getClassLoader());
        }
        System.out.println("当前线程上下文加载器:" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器：" + ServiceLoader.class.getClassLoader());
    }
}
