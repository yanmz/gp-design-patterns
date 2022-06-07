package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解 BeanDefinition 解析示例
 */
public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        annotatedBeanDefinitionReader.setBeanNameGenerator(new BeanNameGenerator() {
            @Override
            public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
                return "aaa";
            }
        });

        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

        annotatedBeanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);

        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();

        AnnotatedBeanDefinitionParsingDemo bean = (AnnotatedBeanDefinitionParsingDemo) beanFactory.getBean("aaa");
        System.out.println(bean);
        System.out.println("加载bean数量: " + (beanDefinitionCountAfter - beanDefinitionCountBefore));
    }
}
