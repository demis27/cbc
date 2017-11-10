package org.demis27.cbc.api.service;

import org.demis27.cbc.api.entity.PersonEntity;
import org.demis27.cbc.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Flux<PersonEntity> findAll() {
        return repository.findAll();
    }

    public Mono<PersonEntity> findById(String id) {
        return repository.findById(id);
    }

    public Mono<PersonEntity> create(PersonEntity person) {
        return repository.insert(person);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
