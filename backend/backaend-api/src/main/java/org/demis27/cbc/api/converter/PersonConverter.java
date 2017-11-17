package org.demis27.cbc.api.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.demis27.cbc.api.dto.PersonDTO;
import org.demis27.cbc.api.entity.PersonEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonConverter extends Converter<PersonEntity, PersonDTO> {

    public PersonDTO convertEntity(PersonEntity entity) {
        if (entity == null)
            return null;
        PersonDTO dto = new PersonDTO();
        dto.id = entity.id;
        dto.firstName = entity.firstName;
        dto.lastName = entity.lastName;
        dto.createDate = entity.createDate;
        dto.lastUpdateDate = entity.lastUpdateDate;

        return dto;
    }

    public PersonEntity convertDTO(PersonDTO dto) {
        PersonEntity entity = new PersonEntity();
        entity.id = dto.id;
        entity.firstName = dto.firstName;
        entity.lastName = dto.lastName;

        return entity;
    }
}
