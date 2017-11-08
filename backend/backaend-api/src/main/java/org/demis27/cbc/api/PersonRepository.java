package org.demis27.cbc.api;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

    public Flux<Person> findAll();
}
