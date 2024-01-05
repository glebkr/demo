package com.example.demo.person.service;

import com.example.demo.person.model.Person;
import com.example.demo.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    public Page<Person> getPageWithPeople(PageRequest pageRequest) {
        return personRepository.findAll(pageRequest);
    }

    public void removePersonById(UUID id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getPersonById(UUID id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void updatePerson(UUID id, Person person) {
        Person personDb = personRepository.findById(id).orElseThrow(() -> new IllegalStateException("The person wasn't found"));
        personDb.setName(person.getName());
    }

}
