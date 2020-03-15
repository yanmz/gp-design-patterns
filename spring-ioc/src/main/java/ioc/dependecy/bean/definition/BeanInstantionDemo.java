package ioc.dependecy.bean.definition;

import ioc.dependecy.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-creation-context.xml");
        User bean = (User) beanFactory.getBean("user-by-static-method");
        User bean1 = (User) beanFactory.getBean("user-by-instance-method");
        User bean2 = (User) beanFactory.getBean("user-by-factory");
        System.out.println(bean);
        System.out.println(bean1);
        System.out.println(bean2);

        System.out.println(bean == bean1);
        System.out.println(bean == bean2);
        System.out.println(bean1 == bean2);

    }
}
