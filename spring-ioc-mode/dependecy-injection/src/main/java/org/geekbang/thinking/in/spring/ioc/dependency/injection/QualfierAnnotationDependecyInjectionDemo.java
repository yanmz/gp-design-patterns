package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 注解的依赖注入
 *
 * @author Tom
 */
public class QualfierAnnotationDependecyInjectionDemo {
    @Autowired
    User user;

    @Autowired
    @Qualifier("user")
    User nameUser;

    @Autowired
    List<User> allUser;


    @Autowired
    @Qualifier
    List<User> qualifierUser;

    @Bean
    @Qualifier
    public User user1() {
        User user = new User();
        user.setId(7L);
        return user;
    }

    @Bean
    @Qualifier
    public User user2() {
        User user = new User();
        user.setId(8L);
        return user;
    }

    @Bean
    @UserGroup
    public User user3() {
        User user = new User();
        user.setId(9L);
        return user;
    }


    @Bean
    @UserGroup
    public User user4() {
        User user = new User();
        user.setId(10L);
        return user;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(QualfierAnnotationDependecyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        QualfierAnnotationDependecyInjectionDemo demo = applicationContext.getBean(QualfierAnnotationDependecyInjectionDemo.class);

        System.out.println("demo.user:" + demo.user);

        System.out.println("demo.nameUser:" + demo.nameUser);

        System.out.println("demo.allUser:" + demo.allUser);

        System.out.println("demo.qualifierUser:" + demo.qualifierUser);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }
}
