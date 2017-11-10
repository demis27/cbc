package org.demis27.cbc.api.dto;

public class PersonDTO {

    public String id;

    public String firstName;

    public String lastName;

    @Override public String toString() {
        return "PersonDTO{" + "id='" + id + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
