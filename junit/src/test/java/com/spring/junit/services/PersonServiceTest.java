package com.spring.junit.services;

import static org.mockito.Mockito.verify;

import com.spring.junit.Dao.PersonDao;
import com.spring.junit.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonDao personRepo;



    private PersonService personService;

    @BeforeEach void setUp()
    {
        this.personService = new PersonService(this.personRepo);
    }

    @Test void getAllPerson()
    {
        personService.getAllPerson();
        verify(personRepo).findAll();
    }
}