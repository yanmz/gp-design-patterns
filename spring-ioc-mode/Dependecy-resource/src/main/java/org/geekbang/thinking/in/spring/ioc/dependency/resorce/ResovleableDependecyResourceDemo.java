package org.geekbang.thinking.in.spring.ioc.dependency.resorce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResovleableDependecy 作为依赖来源
 *
 * @author Tom
 */
public class ResovleableDependecyResourceDemo {

    @Autowired
    String str;

    @PostConstruct
    public void init() {
        System.out.println(str);
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ResovleableDependecyResourceDemo.class);

        ConfigurableListableBeanFactory beanFactory1 = applicationContext.getBeanFactory();
        beanFactory1.registerResolvableDependency(String.class, "Hello,World");

//        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
//            // 注册 Resolvable Dependency
//            beanFactory.registerResolvableDependency(String.class, "Hello,World");
//        });
        applicationContext.refresh();
        applicationContext.close();
    }
}
