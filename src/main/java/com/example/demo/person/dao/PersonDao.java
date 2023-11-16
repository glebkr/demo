package com.example.demo.person.dao;

import com.example.demo.person.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();

    void deletePersonById(UUID id);

    Optional<Person> getPersonById(UUID id);

    void updatePerson (UUID id, Person person);

}
