package org.demis27.cbc.api.entity;

import java.util.ArrayList;
import java.util.List;

public class ComicBookEntity extends Entity {

    public String title;

    public String number;

    public List<PersonEntity> writers = new ArrayList<>();

    @Override public String toString() {
        return "ComicBookEntity{" + "title='" + title + '\'' + ", id='" + id + '\'' + ", createDate=" + createDate + ", lastUpdateDate=" + lastUpdateDate + '}';
    }
}
