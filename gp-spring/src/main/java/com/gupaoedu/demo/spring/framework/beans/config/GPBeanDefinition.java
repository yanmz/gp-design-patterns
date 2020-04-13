package com.gupaoedu.demo.spring.framework.beans.config;

public class GPBeanDefinition {

    //bean的名字
    private String factoryBeanName;

    //bean的全类名
    private String beanClassName;

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}
