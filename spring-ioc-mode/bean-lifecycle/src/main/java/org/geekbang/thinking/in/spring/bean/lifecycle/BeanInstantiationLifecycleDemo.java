package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
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


