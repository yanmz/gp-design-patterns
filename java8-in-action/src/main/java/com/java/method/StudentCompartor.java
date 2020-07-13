package com.java.method;

/**
 * @author Tom
 */
public class StudentCompartor {
    public int compareStudentByScore(Student student1, Student student2) {
        return student1.getAcore() - student2.getAcore();
    }

    public int compareStudentByName(Student student1, Student student2) {
        return student1.getName().compareToIgnoreCase(student2.getName());
    }
}
