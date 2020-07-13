package org.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.geekbang.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author Tom
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        //配置xml文件
        //创建beanfactory对象
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        //依赖来源一:自定义bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
//        System.out.println(userRepository.getUsers());

        System.out.println(userRepository.getBeanFactory() == beanFactory);
        //依赖来源二:依赖注入
        System.out.println(userRepository.getBeanFactory());

//        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
//        System.out.println(userObjectFactory.getObject());

        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(beanFactory == objectFactory.getObject());

        //依赖查找（错误）
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean" + environment);
    }
}
