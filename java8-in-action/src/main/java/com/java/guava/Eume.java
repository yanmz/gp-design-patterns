package com.java.guava;

import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum   Eume {
    CHANGSHAO("1"),
    SHANGHAI("2"),
    HANGZHOU("3");

    private String name;

    private Eume(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static final Map<String,Eume> map= new HashMap<>();

    static {
        for (Eume httpMethod : values()) {
            map.put(httpMethod.name(), httpMethod);
        }
    }

    public static Eume resolve(@Nullable  String method) {
        return (method != null ? map.get(method) : null);
    }
}
