package org.geekbang.thinking.in.spring.ioc.overview.repository;


import javafx.application.Application;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户仓库信息
 */
public class UserRepository {

    private Collection<User> users;//自定义bean

    private BeanFactory beanFactory;//内建非bean对象（依赖）

    private ObjectFactory<ApplicationContext> objectFactory;

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }
    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }
    //    private ObjectFactory<User> userObjectFactory;

//    public ObjectFactory<User> getUserObjectFactory() {
//        return userObjectFactory;
//    }
//    public void setUserObjectFactory(ObjectFactory<User> userObjectFactory) {
//        this.userObjectFactory = userObjectFactory;
//    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
