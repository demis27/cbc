package org.demis27.cbc.api.service;

import org.demis27.cbc.api.dto.ComicBook;
import org.demis27.cbc.api.repository.ComicBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ComicBookService {

    @Autowired
    private ComicBookRepository repository;

    public Flux<ComicBook> findAll() {
        return repository.findAll();
    }

    public Mono<ComicBook> findById(String id) {
        return repository.findById(id);
    }

    public Mono<ComicBook> create(ComicBook ComicBook) {
        return repository.insert(ComicBook);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
