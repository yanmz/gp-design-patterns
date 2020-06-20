package com.gupaoedu.example.rcp.v2;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

@Component
public class ReferenceInvokeProxy implements BeanPostProcessor{

    @Autowired
    RemoteInvocationHandler invocationHandler;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields=bean.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(Reference.class)){
                field.setAccessible(true);
                //针对这个加了GpReference注解的字段，设置为一个代理的值
                Object proxy= Proxy.newProxyInstance(field.getType().getClassLoader(),new Class<?>[]{field.getType()},invocationHandler);
                try {
                    field.set(bean,proxy); //相当于针对加了GpReference的注解，设置了一个代理，这个代理的实现是inovcationHandler
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}