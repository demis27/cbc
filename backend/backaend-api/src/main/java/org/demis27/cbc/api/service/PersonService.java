package org.demis27.cbc.api.service;

import org.demis27.cbc.api.entity.PersonEntity;
import org.demis27.cbc.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends EntityService<PersonEntity> {

    @Autowired
    private PersonRepository repository;

    @Override
    protected MongoRepository<PersonEntity, String> getRepository() {
        return repository;
    }
}
