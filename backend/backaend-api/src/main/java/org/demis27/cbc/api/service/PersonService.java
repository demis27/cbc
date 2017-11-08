package org.demis27.cbc.api.service;

import org.demis27.cbc.api.dto.Person;
import org.demis27.cbc.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Flux<Person> findAll() {
        return repository.findAll();
    }

    public Mono<Person> findById(String id) {
        return repository.findById(id);
    }

    public Mono<Person> create(Person person) {
        return repository.insert(person);
    }

    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }
}
