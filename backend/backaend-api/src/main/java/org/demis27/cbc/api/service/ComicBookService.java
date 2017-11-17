package org.demis27.cbc.api.service;

import java.util.List;
import java.util.Optional;

import org.demis27.cbc.api.entity.ComicBookEntity;
import org.demis27.cbc.api.entity.PersonEntity;
import org.demis27.cbc.api.repository.ComicBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ComicBookService {

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
}
