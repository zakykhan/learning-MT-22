package com.graphDemo.mongoDBgraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphDemo.mongoDBgraphQL.Dao.AuthorRepository;
import com.graphDemo.mongoDBgraphQL.Dao.TutorialRepository;
import com.graphDemo.mongoDBgraphQL.model.Author;
import com.graphDemo.mongoDBgraphQL.model.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final AuthorRepository authorRepository;
    private final TutorialRepository tutorialRepository;

    @Autowired
    public Mutation(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
        this.authorRepository = authorRepository;
        this.tutorialRepository = tutorialRepository;
    }

    public Author createAuthor(String name, Integer age) {
        Author author = new Author();
        author.setName(name);
        author.setAge(age);
        authorRepository.save(author);
        return author;
    }
    public Tutorial createTutorial(String title, String description, String authorId) {
        Tutorial book = new Tutorial();
        book.setAuthor_id(authorId);
        book.setTitle(title);
        book.setDescription(description);
        tutorialRepository.save(book);
        return book;
    }
    public boolean deleteTutorial(String id) {
        tutorialRepository.deleteById(id);
        return true;
    }
    public Tutorial updateTutorial(String id, String title, String description) throws Exception {
        Optional<Tutorial> optTutorial = tutorialRepository.findById(id);
        if (optTutorial.isPresent()) {
            Tutorial tutorial = optTutorial.get();
            if (title != null)
                tutorial.setTitle(title);
            if (description != null)
                tutorial.setDescription(description);
            tutorialRepository.save(tutorial);
            return tutorial;
        }
        throw new Exception("Tutorial is not present!");
    }
}
