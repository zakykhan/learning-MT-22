package com.graphDemo.mongoDBgraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphDemo.mongoDBgraphQL.Dao.AuthorRepository;
import com.graphDemo.mongoDBgraphQL.model.Author;
import com.graphDemo.mongoDBgraphQL.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TutorialResolver implements GraphQLResolver<Tutorial> {

    @Autowired
    private AuthorRepository authorRepository;

    public TutorialResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Author getAuthor(Tutorial tutorial) {
        return authorRepository.findById(tutorial.getAuthor_id()).orElseThrow(null);
    }

}
