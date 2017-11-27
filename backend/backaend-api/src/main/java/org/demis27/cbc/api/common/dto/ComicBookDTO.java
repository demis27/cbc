package org.demis27.cbc.api.common.dto;

import java.util.ArrayList;
import java.util.List;

import org.demis27.cbc.data.entity.PersonEntity;

public class ComicBookDTO extends DTO {

    public String title;

    public String number;

    public List<PersonEntity> writers = new ArrayList<>();

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

}
