package com.design.factory.singtelon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yanmz
 */
public class contanierSingleton {

    private contanierSingleton() {
    }

    public final static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getSingtelon(String name) {//多线程getSingtelon线程不安全
        synchronized (ioc) {
            if (!ioc.containsKey(name)) {
                Object obj = null;
                try {
                    obj = Class.forName(name).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ioc.put(name, obj);
                return obj;
            }
            return ioc.get(name);
        }
    }
}
