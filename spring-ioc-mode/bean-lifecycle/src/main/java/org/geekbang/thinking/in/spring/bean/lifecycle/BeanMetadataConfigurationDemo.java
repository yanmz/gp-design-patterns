package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * properties 解析资源
 *
 * @author Tom
 */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertiesBeanDefinitionReader properties = new PropertiesBeanDefinitionReader(beanFactory);

        String localoads = "MATE-INF/user.properties";

        Resource resource = new ClassPathResource(localoads);

        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        properties.loadBeanDefinitions(encodedResource);

        User bean = beanFactory.getBean(User.class);

        System.out.println(bean);
    }
}
