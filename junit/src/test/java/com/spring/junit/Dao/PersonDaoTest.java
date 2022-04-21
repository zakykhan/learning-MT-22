package com.spring.junit.Dao;


import com.spring.junit.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class PersonDaoTest {

    @Autowired
    private PersonDao personDao;

    void isPersonExistsById(){
        Person person = new Person(786, "zaky", "mehsana");
        personDao.save(person);
        Boolean isExists = personDao.isPersonExitsById(786);
        assertThat(isExists).isTrue();
    }
}
