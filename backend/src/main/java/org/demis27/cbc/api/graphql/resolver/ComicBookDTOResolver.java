package org.demis27.cbc.api.graphql.resolver;

import java.util.ArrayList;
import java.util.List;

import org.demis27.cbc.api.common.converter.PersonConverter;
import org.demis27.cbc.api.common.dto.ComicBookDTO;
import org.demis27.cbc.api.common.dto.PersonDTO;
import org.demis27.cbc.data.entity.PersonEntity;
import org.demis27.cbc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class ComicBookDTOResolver implements GraphQLResolver<ComicBookDTO> {

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private PersonService personService;

    public List<PersonDTO> getWriters(ComicBookDTO comicBook) {
        List<PersonDTO> result = new ArrayList<>();
        for (String writerId : comicBook.getWriters()) {
            PersonEntity writer = personService.findById(writerId);
            result.add(personConverter.convertEntity(writer));
        }
        return result;
    }

}
