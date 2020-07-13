package com.classloader;

/**
 * 当前类加载器(current Classloader)
 * <p>
 * 每个类都会使用自己的类加载器(即加载自身的类加载器)来去加载其他类(指的是依赖的类)
 * 如果classX引用classY，那么classX的类加载器就会去加载classY(前提是classY未被加载)
 * <p>
 * 线程上下文加载器(context classloader)
 * 线程上下文加载器是从jdk1.2开始引入的，类Thread中的getContextClassLoader()与setContextClassLoader(ClassLoader cl)分别来获取和设置上下文类加载器
 * 如果没有通过setContextClassLoader(ClassLoader cl)进行设置的话，线程将继承其父线程的上下文加载器
 * java应用运行时的初始线程的上下文类加载器是系统类加载器。在线程中运行的代码可以通过该类加载器来加载类与资源
 * <p>
 * 线程上下文类加载器的重要性
 * SPI(service provider Interface)
 * 父ClassLoader可以使用当前线程Thread.currentThread().getContextClassLoader()所指定的classloader加载的类
 * 这就改变了父classloader不能使用子classloader或是其他没有直接父子关系的classloader加载的类的情况，即改变了双亲委托模型
 * <p>
 * 线程上下文类加载器就是当前线程的 current classloader
 * <p>
 * 在双亲委托模型下，类加载是下至上的即下层的类加载器会委托上层进行加载。但是对于SPI来说，有些接口是java核心库所提供的，而java核心库是由启动类加载器来加载
 * 而这些接口的实现是来自不同的jar包(厂商提供), java的启动类加载器是不会加载其他来源的jar包，这样传统的双亲委托模型就无法满足SPI的要求。而通过给当前线程
 * 设置上下文类加载器，就可以由设置的上下文类加载器来实现对应接口的加载
 */
public class MyTest24 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.class.getClassLoader());
    }
}
