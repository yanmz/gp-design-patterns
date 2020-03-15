package com.java.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalTest2 {
    public static void main(String[] args) {
        Employee employee1 = new Employee();
        employee1.setName("zhangsna");

        Employee employee2 = new Employee();
        employee2.setName("lisi");

        Company company  = new Company();
        company.setName("wangwu");

        List<Employee> list = Arrays.asList(employee1,employee2);
        company.setEmployees(list);

        Optional<Company> company1 = Optional.ofNullable(company);
        System.out.println(company1.map(theCompany->theCompany.getEmployees()).orElse(Collections.emptyList()));
    }
}
