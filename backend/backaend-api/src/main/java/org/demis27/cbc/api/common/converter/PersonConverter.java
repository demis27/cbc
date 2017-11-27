package org.demis27.cbc.api.common.converter;

import org.demis27.cbc.api.common.dto.PersonDTO;
import org.demis27.cbc.data.entity.PersonEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonConverter extends Converter<PersonEntity, PersonDTO> {

    @Override
    public PersonDTO convertEntity(PersonEntity entity) {
        if (entity == null)
            return null;
        PersonDTO dto = new PersonDTO();
        convertBaseEntity(entity, dto);
        dto.firstName = entity.firstName;
        dto.lastName = entity.lastName;

        return dto;
    }

    @Override
    public PersonEntity convertDTO(PersonDTO dto) {
        if (dto == null)
            return null;
        PersonEntity entity = new PersonEntity();
        convertBaseDto(dto, entity);
        entity.firstName = dto.firstName;
        entity.lastName = dto.lastName;

        return entity;
    }
}
