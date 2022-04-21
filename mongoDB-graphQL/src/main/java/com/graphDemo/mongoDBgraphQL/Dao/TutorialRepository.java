package com.graphDemo.mongoDBgraphQL.Dao;

import com.graphDemo.mongoDBgraphQL.model.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorialRepository extends MongoRepository<Tutorial, String> {
}
