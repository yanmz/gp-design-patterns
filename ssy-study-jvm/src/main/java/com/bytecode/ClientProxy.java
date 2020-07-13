package com.bytecode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Tom
 */
public class ClientProxy {
    public static void main(String[] args) {
        //将生成的代理类输出
        Object aTrue = System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        RealSubject realSubject = new RealSubject();
        InvocationHandler invocationHandler = new DynamicSubject(realSubject);
        Class<?> aClass = realSubject.getClass();
        //将目标类的类加载器 和实现的接口交给代理处理
        Subject subject = (Subject) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), invocationHandler);
        //subject = $Proxy0
        subject.request();
    }
}
