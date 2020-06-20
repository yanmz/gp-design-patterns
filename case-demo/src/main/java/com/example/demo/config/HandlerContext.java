package com.example.demo.config;


import java.util.HashMap;
import java.util.Map;

/**
 * @author cuishuoguo
 * Created on 2019/11/11.
 */
public class HandlerContext {

    private Map<String, Class> map = new HashMap<>();

    private Map<String, AbstractStrategy> beanMap = new HashMap<>();

    public void setBeanMap(Map<String, AbstractStrategy> beanMap) {
        this.beanMap = beanMap;
    }

    public Map<String, Class> getMap() {
        return map;
    }

    public HandlerContext(Map<String, Class> map) {
        this.map = map;
    }

    public Class getClass(String type) {
        Class aClass = map.get(type);
        if (aClass == null) {
            throw new IllegalArgumentException("not found handler for strategy type:" + type);
        }
        return map.get(type);
    }

    public AbstractStrategy getInstance(String type) {
        AbstractStrategy abstractStrategy = beanMap.get(type);
        if (abstractStrategy == null) {
            throw new IllegalArgumentException("not found strategy for type:" + type);
        }
        return beanMap.get(type);
    }


}
