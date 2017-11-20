package org.demis27.cbc.api.converter;

import org.demis27.cbc.api.dto.ComicBookDTO;
import org.demis27.cbc.api.entity.ComicBookEntity;
import org.springframework.stereotype.Service;

@Service
public class ComickBookConverter extends Converter<ComicBookEntity, ComicBookDTO> {

    @Override
    public ComicBookDTO convertEntity(ComicBookEntity entity) {
        if (entity == null)
            return null;
        ComicBookDTO dto = new ComicBookDTO();
        convertBaseEntity(entity, dto);
        dto.title = entity.title;
        dto.number = entity.number;

        return dto;
    }

    @Override
    public ComicBookEntity convertDTO(ComicBookDTO dto) {
        if (dto == null)
            return null;
        ComicBookEntity entity = new ComicBookEntity();
        convertBaseDto(dto, entity);
        entity.title = dto.title;
        entity.writers = dto.writers;

        return entity;
    }
}
