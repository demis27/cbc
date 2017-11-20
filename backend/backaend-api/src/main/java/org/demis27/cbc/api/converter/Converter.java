package org.demis27.cbc.api.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.demis27.cbc.api.dto.DTO;
import org.demis27.cbc.api.entity.Entity;

public abstract class Converter<E extends Entity, D extends DTO> {

    public List<D> convertEntities(List<E> entities) {
        if (entities != null && entities.size() > 0) {
            return entities.stream().map(person -> convertEntity(person)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public List<E> convertDTOs(List<D> dtos) {
        if (dtos != null && dtos.size() > 0) {
            return dtos.stream().map(person -> convertDTO(person)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public abstract D convertEntity(E entity);

    public abstract E convertDTO(D dto);

    public void convertBaseEntity(E entity, D dto) {
        dto.id = entity.id;
        dto.createDate = entity.createDate;
        dto.lastUpdateDate = entity.lastUpdateDate;
    }

    public void convertBaseDto(D dto, E entity) {
        entity.id = dto.id;
    }
}
