package com.java.stream;

public class Student {
    private String name;
    private String age;
    private int core;

    public Student(String name, Integer core, String age) {
        this.name = name;
        this.age = age;
        this.core = core;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getCore() {
        return core;
    }

    public void setCore(Integer core) {
        this.core = core;
    }

}
