package com.java.method;

public class Student {
    private String name;
    private int acore;

    public Student(String name, int acore) {
        this.name = name;
        this.acore = acore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAcore() {
        return acore;
    }

    public void setAcore(int acore) {
        this.acore = acore;
    }

    public static int compareStudentByScore(Student student1, Student student2) {
        return student1.getAcore() - student2.getAcore();
    }

    public static int compareStudentByName(Student student1, Student student2) {
        return student1.getName().compareToIgnoreCase(student2.getName());
    }
}
