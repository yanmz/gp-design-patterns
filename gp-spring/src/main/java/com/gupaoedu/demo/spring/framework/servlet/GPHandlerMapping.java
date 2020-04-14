package com.gupaoedu.demo.spring.framework.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Created by Tom.
 */
public class GPHandlerMapping {
    private Pattern pattern;     //URL
    private Method method;  //对应的Method
    private Object controller;//Method对应的实例对象

    public GPHandlerMapping(Pattern pattern, Object controller, Method method) {
        this.pattern = pattern;
        this.method = method;
        this.controller = controller;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
