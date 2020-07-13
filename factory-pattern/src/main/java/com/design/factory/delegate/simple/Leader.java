package com.design.factory.delegate.simple;

public class Leader implements IEmployee {
    protected String goodAt = "编程";

    @Override
    public void doing(String task) {
        if ("爬虫".equals(task)) {
            new IEmployeeA().doing(task);
        }
        if ("设计".equals(task)) {
            new IEmployeeB().doing(task);
        }
    }
}
