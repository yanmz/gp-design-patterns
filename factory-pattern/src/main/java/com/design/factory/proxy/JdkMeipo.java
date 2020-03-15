package com.design.factory.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkMeipo implements InvocationHandler {
    private IPerson  iPerson;
    public   IPerson  getInstace(IPerson iPerson){
        this.iPerson = iPerson;
        Class<? extends IPerson> aClass = iPerson.getClass();
     return (IPerson) Proxy.newProxyInstance(aClass.getClassLoader(),aClass.getInterfaces(),this);
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass());
        after();
        Object object =  method.invoke(this.iPerson,args);
        before();
        return object;
    }
    private void after() {
        System.out.println("双方同意，开始交往");
    }

    private void before() {
        System.out.println("我是媒婆，已经收集到你的需求，开始物色");
    }

}
