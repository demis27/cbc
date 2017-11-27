package org.demis27.cbc.data.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
public class PersonEntity extends Entity {

    public String firstName;

    public String lastName;

    @Override public String toString() {
        return "PersonEntity{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", id='" + id + '\'' + ", createDate=" + createDate
                + ", lastUpdateDate=" + lastUpdateDate + '}';
    }
}
