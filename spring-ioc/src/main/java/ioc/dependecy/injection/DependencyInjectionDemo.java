package ioc.dependecy.injection;

import ioc.dependecy.repository.UserRepositroy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;

/**
 * 依赖注入示列
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        UserRepositroy userRepository = (UserRepositroy) beanFactory.getBean("userRepository");
//        System.out.println(userRepository.getUsers());
        //依赖注入
        System.out.println(userRepository.getBeanFactory());
        System.out.println(beanFactory);
//        System.out.println(userRepository.getBeanFactory()==beanFactory);
        //依赖查找
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject() == beanFactory);
    }
}
