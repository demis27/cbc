package org.demis27.cbc.api.common.dto;

import java.util.ArrayList;
import java.util.List;

public class ComicBookDTO extends DTO {

    public String title;

    public String number;

    public List<String> writers = new ArrayList<>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }
}
