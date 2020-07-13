package com.design.factory.delegate.simple;

public class IEmployeeB implements IEmployee {
    protected String goodAt = "设计";

    @Override
    public void doing(String task) {
        System.out.println("员工B" + goodAt + "----------------" + task);
    }
}
