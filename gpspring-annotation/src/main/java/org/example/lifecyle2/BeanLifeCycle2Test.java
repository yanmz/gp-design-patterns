package org.example.lifecyle2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycle2Test {

    public static void main(String[] args) {
        System.out.println("=====================现在开始初始化容器================");
        ApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");

        System.out.println("======================容器初始化成功=====================");
        //得到Preson，并使用
        PersonBean person = factory.getBean("person", PersonBean.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }
}
