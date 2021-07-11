package org.example.imports;

import org.example.imports.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by Tom.
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata  当前类的注解信息
     * @param registry 完成BeanDefinition的注册
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean company = registry.containsBeanDefinition("company");
        boolean member = registry.containsBeanDefinition("member");

        if(company && member){
            /**
             * 手动注册BeanDefinition<br>
             */
            BeanDefinition beanDefinition = new RootBeanDefinition(User.class);
            registry.registerBeanDefinition("user",beanDefinition);
        }else {
            System.out.println("company和member在执行到MyImportBeanDefintionRegistar的时候还不存在");
        }
    }
}
