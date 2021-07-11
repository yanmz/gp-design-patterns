package org.example.lazy;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfig {

    //懒加载只针对单例Bean起作用，默认非延时加载
    //延迟加载容器启动不创建对象，调用对象功能的时候才创建
    @Bean
    public Person person(){
        System.out.println("将person对象添加到IoC容器中");
        return new Person("Tom",18);
    }


    @Lazy
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Person lazyPerson(){
        System.out.println("将lazyPerson对象添加到IoC容器中");
        return new Person("Tom",19);
    }
}
