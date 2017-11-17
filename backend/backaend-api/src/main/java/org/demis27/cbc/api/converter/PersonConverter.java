package org.demis27.cbc.api.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.demis27.cbc.api.dto.PersonDTO;
import org.demis27.cbc.api.entity.PersonEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonConverter {

    public List<PersonDTO> convertEntities(List<PersonEntity> entities) {
        if (entities != null && entities.size() > 0) {
            return entities.stream().map(person -> convertEntity(person)).collect(Collectors.toList());
        }
        else {
            return Collections.emptyList();
        }
    }

    public PersonDTO convertEntity(PersonEntity entity) {
        if (entity == null)
            return null;
        PersonDTO dto = new PersonDTO();
        dto.id = entity.id;
        dto.firstName = entity.firstName;
        dto.lastName = entity.lastName;

        return dto;
    }

    public List<PersonEntity> convertDTOs(List<PersonDTO> dtos) {
        if (dtos != null && dtos.size() > 0) {
            return dtos.stream().map(person -> convertDTO(person)).collect(Collectors.toList());
        }
        else {
            return Collections.emptyList();
        }
    }

    public PersonEntity convertDTO(PersonDTO dto) {
        PersonEntity entity = new PersonEntity();
        entity.id = dto.id;
        entity.firstName = dto.firstName;
        entity.lastName = dto.lastName;

        return entity;
    }
}
