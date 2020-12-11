package org.geekbang.thinking.in.spring.ioc.dependency.resorce;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例:可以通过spring内建依赖bean作为依赖来源，其中依赖来源分为三种 自定义bean  sprin内建bean(spring 容器启动时就初始化bean)  spring内建依赖bean
 *
 * @author Tom
 */
public class DenpendecyReorceDemo {

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    ResourceLoader resourceLoader;

    @PostConstruct
    public void initByInjection() {
        System.out.println("demo.beanFactory: " + (beanFactory == applicationContext));
        System.out.println("demo.beanFactory: " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("demo.applicationEventPublisher: " + (applicationEventPublisher == applicationContext));
        System.out.println("demo.resourceLoader: " + (resourceLoader == applicationContext));
    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + " 无法在 BeanFactory 中查找!");
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(DenpendecyReorceDemo.class);

        applicationContext.refresh();

        DenpendecyReorceDemo demo = applicationContext.getBean(DenpendecyReorceDemo.class);


        applicationContext.close();
    }
}
