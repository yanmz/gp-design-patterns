package com.design.factory.delegate.simple;

public class IEmployeeA  implements  IEmployee{
    protected String goodAt="编程";
    @Override
    public void doing(String task) {
        System.out.println("员工A"+goodAt+"----------------"+task);
    }
}
