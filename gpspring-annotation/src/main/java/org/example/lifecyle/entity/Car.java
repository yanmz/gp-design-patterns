package org.example.lifecyle.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by Tom.
 */
public class Car implements InitializingBean, DisposableBean {
    public Car() {
        System.out.println("调用Car的构造方法\n");
    }

    public void addOil() {
        System.out.println("自定义方法初始化\n");
    }

    public void close() {
        System.out.println("自定义方法销毁\n");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Car 实现DisposableBean接口destroy()销毁\n");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Car 实现DisposableBean接口afterPropertiesSet()初始化\n");
    }

    @PostConstruct
    public void PostConstruct() {
        System.out.println("Car 通过@PostConstruct初始化\n");
    }

    @PreDestroy
    public void PreDestroy() {
        System.out.println("Car 通过@PreDestroy销毁\n");
    }
}
