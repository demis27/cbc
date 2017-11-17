package org.demis27.cbc.api.entity;

public class ComicBookEntity extends Entity {

    public String title;

    @Override public String toString() {
        return "ComicBookEntity{" + "title='" + title + '\'' + ", id='" + id + '\'' + ", createDate=" + createDate + ", lastUpdateDate=" + lastUpdateDate + '}';
    }
}
