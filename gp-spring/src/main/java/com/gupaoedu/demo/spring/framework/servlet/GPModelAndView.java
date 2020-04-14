package com.gupaoedu.demo.spring.framework.servlet;

import java.util.Map;

/**
 * Created by Tom.
 */
public class GPModelAndView {
    private String viewName;
    private Map<String,?> model;

    public GPModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public GPModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}


