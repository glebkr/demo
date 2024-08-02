package com.example.demo.person.repository;

import com.example.demo.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    @Query(value = "SELECT p FROM Person p")
    public List<Person> getAllPersons();

}
