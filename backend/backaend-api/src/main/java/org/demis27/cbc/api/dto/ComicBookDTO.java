package org.demis27.cbc.api.dto;

import java.util.ArrayList;
import java.util.List;

import org.demis27.cbc.api.entity.PersonEntity;

public class ComicBookDTO extends DTO {

    public String title;

    public String number;

    public List<PersonEntity> writers = new ArrayList<>();

}
