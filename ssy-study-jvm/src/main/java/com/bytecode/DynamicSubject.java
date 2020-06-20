package com.bytecode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {
    private Subject sub;

    DynamicSubject(Subject sub){
        this.sub =sub;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before **************");

        method.invoke(sub,args);

        System.out.println("After *****************");
        return  null;
    }
}
