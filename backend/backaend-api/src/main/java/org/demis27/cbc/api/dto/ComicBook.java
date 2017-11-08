package org.demis27.cbc.api.dto;

public class ComicBook {

    public String title;

    public String id;

    @Override public String toString() {
        return "ComicBook{" + "title='" + title + '\'' + ", id='" + id + '\'' + '}';
    }
}
