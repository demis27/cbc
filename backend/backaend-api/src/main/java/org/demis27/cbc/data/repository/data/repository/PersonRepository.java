package org.demis27.cbc.data.repository.data.repository;

import org.demis27.cbc.data.repository.data.entity.PersonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<PersonEntity, String> {
}
