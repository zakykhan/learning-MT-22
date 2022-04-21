package com.spring.junit.Dao;


import com.spring.junit.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonDao
        extends JpaRepository<Person, Integer> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Person s WHERE s.personId = ?1")
    Boolean isPersonExitsById(Integer id);
}
