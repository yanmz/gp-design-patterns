package com.gupaoedu.demo.spring.framework.aop.aspect;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * Created by Tom.
 */
@Data
public class GPAdvice {
    private Object aspect;
    private Method adviceMethod;
    private String throwName;

    public GPAdvice(Object aspect, Method adviceMethod) {
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
    }

}
