package org.demis27.cbc.api.converter;

import org.demis27.cbc.api.dto.PersonDTO;
import org.demis27.cbc.api.entity.PersonEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonConverter {

    public Mono<PersonDTO> convertEntity(Mono<PersonEntity> entity) {
        return entity.map(person -> convertEntity(person));
    }

    public Flux<PersonDTO> convertEntities(Flux<PersonEntity> entities) {
        return entities.map(person -> convertEntity(person));
    }

    public PersonDTO convertEntity(PersonEntity entity) {
        PersonDTO dto = new PersonDTO();
        dto.id = entity.id;
        dto.firstName = entity.firstName;
        dto.lastName = entity.lastName;

        return dto;
    }

    public Mono<PersonEntity> convertDTO(Mono<PersonDTO> dto) {
        return dto.map(person -> convertDTO(person));
    }

    public Flux<PersonEntity> convertDTOs(Flux<PersonDTO> dtos) {
        return dtos.map(person -> convertDTO(person));
    }

    public PersonEntity convertDTO(PersonDTO dto) {
        PersonEntity entity = new PersonEntity();
        entity.id = dto.id;
        entity.firstName = dto.firstName;
        entity.lastName = dto.lastName;

        return entity;
    }
}
