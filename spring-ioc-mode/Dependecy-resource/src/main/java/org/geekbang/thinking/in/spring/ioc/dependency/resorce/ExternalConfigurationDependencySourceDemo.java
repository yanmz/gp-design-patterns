package org.geekbang.thinking.in.spring.ioc.dependency.resorce;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource(value = "classpath:/META-INF/default.properties",encoding = "utf-8")
public class ExternalConfigurationDependencySourceDemo {
    @Value("${user.id:1}")
    private String str;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath:/META-INF/default.properties}")
    private Resource resource;

    @PostConstruct
    public void postConstruct() {
        System.out.println(str + "---------" + name + "-------------" + resource);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

        applicationContext.refresh();

        applicationContext.close();
    }
}
