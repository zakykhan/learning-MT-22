package com.graphDemo.mongoDBgraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphDemo.mongoDBgraphQL.Dao.AuthorRepository;
import com.graphDemo.mongoDBgraphQL.Dao.TutorialRepository;
import com.graphDemo.mongoDBgraphQL.model.Author;
import com.graphDemo.mongoDBgraphQL.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {


    private final AuthorRepository authorRepository;
    private final TutorialRepository tutorialRepository;

    @Autowired
    public Query(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
        this.authorRepository = authorRepository;
        this.tutorialRepository = tutorialRepository;
    }
    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
    public Iterable<Tutorial> findAllTutorials() {
        return tutorialRepository.findAll();
    }
    public long countAuthors() {
        return authorRepository.count();
    }
    public long countTutorials() {
        return tutorialRepository.count();
    }
}
