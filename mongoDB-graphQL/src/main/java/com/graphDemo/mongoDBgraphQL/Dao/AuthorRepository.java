package com.graphDemo.mongoDBgraphQL.Dao;

import com.graphDemo.mongoDBgraphQL.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
