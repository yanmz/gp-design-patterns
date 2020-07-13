package com.java.guava;

import lombok.Data;

import java.util.List;

@Data
public class Date implements Cloneable {
    private List list;

    @Override
    public String toString() {
        return "Date{" +
                "list=" + list +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }
}
