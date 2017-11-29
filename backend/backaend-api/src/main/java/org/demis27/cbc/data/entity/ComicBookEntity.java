package org.demis27.cbc.data.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comicBook")
public class ComicBookEntity extends Entity {

    public String title;

    public String number;

    public List<String> writers = new ArrayList<>();

    @Override public String toString() {
        return "ComicBookEntity{" + "title='" + title + '\'' + ", id='" + id + '\'' + ", createDate=" + createDate + ", lastUpdateDate=" + lastUpdateDate + '}';
    }
}
