package com.java.guava;
import java.util.Arrays;

public class MyTest4 {

    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date();
        date.setList(Arrays.asList("1","10"));
        Parent parent = new Parent("xiaoyan",18, Arrays.asList("1","2","3"),date);

        Parent clone = parent.clone();
        System.out.println("Parent："+parent);
        System.out.println("克隆的Parent："+clone);

        parent.getDate().setList(Arrays.asList("1","11"));


        clone.getDate().setList(Arrays.asList("20"));

        System.out.println("修改后的原Parent："+parent);
        System.out.println("修改后的克隆Parent："+clone);
    }

}
