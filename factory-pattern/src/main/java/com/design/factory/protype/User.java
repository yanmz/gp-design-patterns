package com.design.factory.protype;


import java.util.List;

/**
 * @author Tom
 */
public class User {
    private int  id;
    private String name;
    private int age;
    private List<String> hobiies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getHobiies() {
        return hobiies;
    }

    public void setHobiies(List<String> hobiies) {
        this.hobiies = hobiies;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobiies=" + hobiies +
                '}';
    }
}
