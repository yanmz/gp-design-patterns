package ioc.dependecy.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct  :UserFactory:初始化。。。");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法 initUserFactory() : UserFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() : UserFactory 初始化中...");
    }

    @PreDestroy
    public void PreDetory() {
        System.out.println("PreDetory()销毁中。。。");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy()  销毁中。。。");
    }

    public void dodestory() {
        System.out.println("自定义销毁方法 销毁中。。。");
    }
}
