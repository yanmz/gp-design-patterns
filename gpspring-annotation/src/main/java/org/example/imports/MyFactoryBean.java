package org.example.imports;

import org.example.imports.entity.Monkey;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/**
 * Created by Tom.
 */
public class MyFactoryBean implements FactoryBean<Monkey> {
    @Nullable
    @Override
    public Monkey getObject() throws Exception {
        return new Monkey();
        //Proxy.newProxyInstance()
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return Monkey.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
