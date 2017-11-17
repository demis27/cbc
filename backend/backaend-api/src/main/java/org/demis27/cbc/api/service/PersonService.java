package org.demis27.cbc.api.service;

import java.util.List;
import java.util.Optional;

import org.demis27.cbc.api.entity.PersonEntity;
import org.demis27.cbc.api.range.Range;
import org.demis27.cbc.api.repository.PersonRepository;
import org.demis27.cbc.api.sort.SortParameterElement;
import org.demis27.cbc.api.sort.SortParameterElementConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public List<PersonEntity> findAll() {
        return repository.findAll();
    }

    public List<PersonEntity> findPart(Range range, List<SortParameterElement> sorts) {
        if (sorts == null || sorts.size() == 0) {
            return repository.findAll(PageRequest.of(range.getPage(), range.getSize())).getContent();
        } else {
            return repository
                    .findAll(PageRequest.of(range.getPage(), range.getSize(), SortParameterElementConverter.convert(sorts)))
                    .getContent();
        }
    }

    public PersonEntity findById(String id) {
        Optional<PersonEntity> person = repository.findById(id);
        if (person.isPresent())
            return person.get();
        else
            return null;
    }

    public PersonEntity create(PersonEntity person) {
        return repository.insert(person);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public PersonEntity update(PersonEntity person) {
        return repository.save(person);
    }
}
