package org.demis27.cbc.service;

import java.util.List;
import java.util.Optional;

import org.demis27.cbc.data.entity.ComicBookEntity;
import org.demis27.cbc.data.repository.ComicBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class ComicBookService extends EntityService<ComicBookEntity> {

    @Autowired
    private ComicBookRepository repository;

    public List<ComicBookEntity> findAll() {
        return repository.findAll();
    }

    public ComicBookEntity findById(String id) {
        Optional<ComicBookEntity> comicBookEntity = repository.findById(id);
        if (comicBookEntity.isPresent())
            return comicBookEntity.get();
        else
            return null;
    }

    public ComicBookEntity create(ComicBookEntity ComicBook) {
        return repository.insert(ComicBook);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override protected MongoRepository<ComicBookEntity, String> getRepository() {
        return repository;
    }
}
