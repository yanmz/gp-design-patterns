package org.geekbang.thinking.in.spring.ioc.bean.scope;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;


public class BeanScopeDemo implements DisposableBean {

    @Bean
    public User userSingleton() {
        return creatUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User userPrototype() {
        return creatUser();
    }

    public User creatUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("userSingleton")
    private User singletonUser;

    @Autowired
    @Qualifier("userPrototype")
    private User protoTypeUser;

    @Autowired
    @Qualifier("userPrototype")
    private User protoTypeUser1;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;


    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor((beanFactory) -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称:%s 在初始化前回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        applicationContext.addBeanFactoryPostProcessor((beanFactory) -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称:%s 在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        applicationContext.refresh();

        //结论一：
        //Singleton Bean 无论依赖查找还是依赖注入 都是同一个对象
        //prototype Bean 无论依赖查找还是依赖注入 都是新的对象

        //结论二：
        //如果依赖注入集合类型的对象，Singleton bean与prototype bean都只有一个
        //prototype Bean 有别于其他地方的依赖注入prototype  Bean

        //结论三：
        //无论是singleton 还是prototype都会执行初始化回调
        //不过singleton会执行销毁回调方法


        scopeBeanByLookup(applicationContext);

        scopeBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopeBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo bean = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("bean.singletonUser = " + bean.singletonUser);
        System.out.println("bean.protoTypeUser = " + bean.protoTypeUser);
        System.out.println("bean.protoTypeUser1 = " + bean.protoTypeUser1);
        System.out.println("-------------------------------------------------------------");
        System.out.println("bean.users = " + bean.users);
        System.out.println("-------------------------------------------------------------");
    }

    private static void scopeBeanByLookup(AnnotationConfigApplicationContext applicationContext) {

        for (int i = 0; i < 3; i++) {
            User userSingleton = applicationContext.getBean("userSingleton", User.class);
            System.out.println("userSingleton = " + userSingleton);

            User userPrototype = applicationContext.getBean("userPrototype", User.class);
            System.out.println("userPrototype = " + userPrototype);
        }
    }

    @Override
    public void destroy() throws Exception {

        System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");

        this.protoTypeUser.destroy();
        this.protoTypeUser1.destroy();
        // 获取 BeanDefinition
        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) { // 如果当前 Bean 是 prototype scope
                User user = entry.getValue();
                user.destroy();
            }
        }
        System.out.println("当前 BeanScopeDemo Bean 销毁完成");
    }
}
