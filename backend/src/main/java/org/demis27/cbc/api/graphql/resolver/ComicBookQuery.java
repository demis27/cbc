package org.demis27.cbc.api.graphql.resolver;

import java.util.List;

import org.demis27.cbc.api.common.converter.ComickBookConverter;
import org.demis27.cbc.api.common.dto.ComicBookDTO;
import org.demis27.cbc.service.ComicBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

@Component
public class ComicBookQuery implements GraphQLRootResolver {

    private final ComicBookService service;

    private final ComickBookConverter converter;

    @Autowired
    public ComicBookQuery(ComicBookService service, ComickBookConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    public List<ComicBookDTO> allComicBooks() {
        return converter.convertEntities(service.findAll());
    }
}
