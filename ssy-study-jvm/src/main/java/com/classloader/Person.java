package com.classloader;

public class Person {
    private Person person;

    public void setPerson(Object object) {
        this.person = (Person) object;
    }
}
