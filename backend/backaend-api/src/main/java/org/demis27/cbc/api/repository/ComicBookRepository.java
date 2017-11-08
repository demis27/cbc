package org.demis27.cbc.api.repository;

import org.demis27.cbc.api.dto.ComicBook;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicBookRepository extends ReactiveMongoRepository<ComicBook, String>  {

}
