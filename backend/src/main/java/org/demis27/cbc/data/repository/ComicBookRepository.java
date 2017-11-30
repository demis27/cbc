package org.demis27.cbc.data.repository;

import org.demis27.cbc.data.entity.ComicBookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicBookRepository extends MongoRepository<ComicBookEntity, String> {

}
