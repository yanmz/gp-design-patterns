package com.java.guava;

import lombok.Data;

import java.util.List;

@Data
public class Parent implements Cloneable {
    private String name;
    private int age;
    private List list;
    private Date date;


    public Parent() {

    }

    public Parent(String name, int age, List list, Date date) {
        this.name = name;
        this.age = age;
        this.list = list;
        this.date = date;
    }

    @Override
    public Parent clone() throws CloneNotSupportedException {
        Parent parent = (Parent) super.clone();
        parent.date = (Date) this.date.clone();
        return parent;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", list=" + list +
                ", date=" + date +
                '}';
    }
}
