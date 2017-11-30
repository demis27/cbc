package org.demis27.cbc.api.graphql.resolver;

import java.util.List;

import org.demis27.cbc.api.common.controller.ControllerHelper;
import org.demis27.cbc.api.common.converter.ComickBookConverter;
import org.demis27.cbc.api.common.dto.ComicBookDTO;
import org.demis27.cbc.api.common.range.Range;
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

    /**
     * Get the list of comics with authors.
     *
     * Query example: { "query": "{allComicBooks(pageNumber: 0, pageSize: 10, sort: \"title\") {title number writers {firstName lastName}}}" }
     *
     * @param pageNumber The number of the page.
     * @param pageSize The size of the page.
     * @param sort The sort parameters of the list.
     *
     * @return A paginate and sorted list of comic books.
     */
    public List<ComicBookDTO> allComicBooks(Integer pageNumber, Integer pageSize, String sort) {
        return converter.convertEntities(
                service.findPart(new Range(pageNumber != null ? pageNumber : 0, pageSize != null ? pageSize : 0), ControllerHelper.getSorts(sort)));
    }
}
