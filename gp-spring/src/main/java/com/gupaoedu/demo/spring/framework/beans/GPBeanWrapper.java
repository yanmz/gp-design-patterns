package com.gupaoedu.demo.spring.framework.beans;

public class GPBeanWrapper {

    //类的实例
    private Object wrapperInstance;
    //类的class对象
    private Class<?> wrappedClass;

    public GPBeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.wrappedClass = instance.getClass();
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }
}
