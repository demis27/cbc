package org.demis27.cbc.service;

import java.util.List;
import java.util.Optional;

import org.demis27.cbc.data.entity.Entity;
import org.demis27.cbc.api.common.range.Range;
import org.demis27.cbc.api.common.sort.SortParameterElement;
import org.demis27.cbc.api.common.sort.SortParameterElementConverter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract class EntityService<E extends Entity> {

    public List<E> findPart(Range range, List<SortParameterElement> sorts) {
        if (sorts == null || sorts.size() == 0) {
            return getRepository().findAll(PageRequest.of(range.getPage(), range.getSize())).getContent();
        } else {
            return getRepository()
                    .findAll(PageRequest.of(range.getPage(), range.getSize(), SortParameterElementConverter.convert(sorts)))
                    .getContent();
        }
    }

    public E findById(String id) {
        Optional<E> result = getRepository().findById(id);
        if (result.isPresent())
            return result.get();
        else
            return null;
    }

    public E create(E entity) {
        return getRepository().insert(entity);
    }

    public E update(E person) {
        return getRepository().save(person);
    }

    public void delete(String id) {
        getRepository().deleteById(id);
    }

    protected abstract MongoRepository<E, String> getRepository();

}
