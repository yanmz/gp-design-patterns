package org.example.lifecyle2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("这是BeanPostProcessor实现类构造器！！");
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out.println("MyBeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！arg0=" + arg0 + " arg1=" + arg1);
        return arg0;
    }

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out.println("MyBeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！arg0=" + arg0 + "arg1=" + arg1);
        return arg0;
    }
}