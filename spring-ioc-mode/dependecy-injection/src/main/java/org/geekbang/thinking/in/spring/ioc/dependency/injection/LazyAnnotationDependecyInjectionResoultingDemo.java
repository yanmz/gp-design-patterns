package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * 注解驱动的依赖注入处理过程
 * @author Tom
 */
public class LazyAnnotationDependecyInjectionResoultingDemo {
    @Autowired
    User user;//实时查找


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext =new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(LazyAnnotationDependecyInjectionResoultingDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        LazyAnnotationDependecyInjectionResoultingDemo demo =  applicationContext.getBean(LazyAnnotationDependecyInjectionResoultingDemo.class);

        System.out.println("demo.user:"+demo.user);
        System.out.println("--------------------------------");


        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }
}
