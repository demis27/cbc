package org.demis27.cbc.api.graphql.resolver;

import org.demis27.cbc.api.common.converter.ComickBookConverter;
import org.demis27.cbc.api.common.dto.ComicBookDTO;
import org.demis27.cbc.service.ComicBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

@Component
public class ComicBookMutation implements GraphQLRootResolver {

    private final ComicBookService service;

    private final ComickBookConverter converter;

    @Autowired
    public ComicBookMutation(ComicBookService service, ComickBookConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    public ComicBookDTO createComicBook(String title, String number) {
        ComicBookDTO dto = new ComicBookDTO();
        dto.title = title;
        dto.number = number;
        service.create(converter.convertDTO(dto));
        return dto;
    }
}
