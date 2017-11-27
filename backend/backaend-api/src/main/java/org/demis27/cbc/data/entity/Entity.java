package org.demis27.cbc.data.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

public abstract class Entity implements Persistable<String> {

    @Id
    public String id;

    @CreatedDate
    public Date createDate;

    @LastModifiedDate
    public Date lastUpdateDate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
