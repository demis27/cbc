package org.demis27.cbc.api.dto;

import java.util.Date;

public class PersonDTO {

    public String id;

    public String firstName;

    public String lastName;

    public Date createDate;

    public Date lastUpdateDate;

    @Override public String toString() {
        return "PersonDTO{" + "id='" + id + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
