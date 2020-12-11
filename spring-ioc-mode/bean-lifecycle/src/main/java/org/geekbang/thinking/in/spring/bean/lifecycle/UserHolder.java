package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/11/18 16:13
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        EnvironmentAware, InitializingBean, SmartInitializingSingleton, DisposableBean {

    private final User user;

    private Integer number;

    private String description;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private String beanName;

    private Environment environment;

    public User getUser() {
        return user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @PostConstruct
    public void initPostConstruct() {
        this.description = "The user  holder  V4";
        System.out.println("initPostConstruct() = " + description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = "The  user holder V5";
        System.out.println("afterPropertiesSet() = " + description);
    }


    public void init() {
        this.description = "The user holder V6";
        System.out.println("init() = " + description);
    }


    @Override
    public void afterSingletonsInstantiated() {
        this.description = "The  user holder V8";
        System.out.println("afterSingletonsInstantiated() = " + description);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PreDestroy
    public void preDestroy() {
        // postProcessBeforeDestruction : The user holder V9
        this.description = "The user holder V10";
        System.out.println("preDestroy() = " + description);
    }

    @Override
    public void destroy() throws Exception {
        // preDestroy : The user holder V10
        this.description = "The user holder V11";
        System.out.println("destroy() = " + description);
    }

    public void doDestroy() {
        // destroy : The user holder V11
        this.description = "The user holder V12";
        System.out.println("doDestroy() = " + description);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("The UserHolder is finalized...");
    }
}
