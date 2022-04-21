package com.spring.junit.service;

import java.util.List;

import com.spring.junit.Dao.PersonDao;
import com.spring.junit.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonDao repo;

    public List<Person> getAllPerson()
    {
        return this.repo.findAll();
    }

    public PersonService(PersonDao repo)
    {
        this.repo = repo;
    }
}
