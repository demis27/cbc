package org.demis27.cbc.api.dto;

public class Person {

    public String id;

    public String firstName;

    public String lastName;

    @Override public String toString() {
        return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", id='" + id + '\'' + '}';
    }
}
