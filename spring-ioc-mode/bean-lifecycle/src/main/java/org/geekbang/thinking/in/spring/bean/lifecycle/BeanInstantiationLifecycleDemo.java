package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *  *

 总结InstantiationAwareBeanPostProcessor方法:
 postProcessBeforeInstantiation()在bean实例化前回调,返回实例则不对bean实例化,返回null则进行spring bean实例化(doCreateBean);
 postProcessAfterInstantiation()在bean实例化后在填充bean属性之前回调,返回true则进行下一步的属性填充,返回false:则不进行属性填充
 postProcessProperties在属性赋值前的回调在applyPropertyValues之前操作可以对属性添加或修改等操作最后在通过applyPropertyValues应用bean对应的wapper对象

 * @author yanmz
 * @version 1.0
 * @date 2020/10/16 17:09
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        executeApplicationContext();
        System.out.println("-------------------------------");
        executeBeanfactory();

    }

    private static void executeBeanfactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        String[] locations = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
        // 通过 Bean Id 和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);


        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    public static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        String[] locations = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        applicationContext.refresh();
        // 通过 Bean Id 和类型进行依赖查找
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        User superUser = applicationContext.getBean("superUser", User.class);
        System.out.println(superUser);


        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();

    }
}


