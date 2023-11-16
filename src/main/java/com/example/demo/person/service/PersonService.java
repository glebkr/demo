package com.example.demo.person.service;

import com.example.demo.person.dao.PersonDao;
import com.example.demo.person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public void removePersonById(UUID id) {
        personDao.deletePersonById(id);
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.getPersonById(id);
    }

    public void updatePerson(UUID id, Person person) {
        personDao.updatePerson(id, person);
    }

}
