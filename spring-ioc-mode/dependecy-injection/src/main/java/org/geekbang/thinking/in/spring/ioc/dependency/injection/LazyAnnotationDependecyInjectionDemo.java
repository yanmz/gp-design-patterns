package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class LazyAnnotationDependecyInjectionDemo {
    @Autowired
    User user;//实时查找

    @Autowired
    ObjectProvider<User> objectProvider;

    @Autowired
    ObjectFactory<List<User>> objectFactory;


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(LazyAnnotationDependecyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        LazyAnnotationDependecyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependecyInjectionDemo.class);

        System.out.println("demo.user:" + demo.user);
        System.out.println("--------------------------------");
        System.out.println("demo.objectProvider:" + demo.objectProvider.getObject());
        System.out.println("--------------------------------");
        demo.objectProvider.stream().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("demo.objectFactory:" + demo.objectFactory.getObject());


        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }
}
