package org.demis27.cbc.api.repository;

import org.demis27.cbc.api.entity.ComicBookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicBookRepository extends MongoRepository<ComicBookEntity, String> {

}
