package org.demis27.cbc.api.entity;

public class PersonEntity {

    public String id;

    public String firstName;

    public String lastName;

    @Override public String toString() {
        return "PersonEntity{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", id='" + id + '\'' + '}';
    }
}
