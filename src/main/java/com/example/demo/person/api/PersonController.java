package com.example.demo.person.api;

import com.example.demo.person.model.Person;
import com.example.demo.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/api/v1/person")
@RestController
@EnableCaching
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Cacheable(value = "persons")
    public List<Person> getAllPeople() {
        return personService.getAllPersons();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"offset", "limit"})
    public Page<Person> getPageWithPeople(@RequestParam(value = "offset") Integer offset, @RequestParam(value = "limit") Integer limit) {
        return personService.getPageWithPeople(PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "name")));
    }

    @GetMapping(path = "{id}")
    @Cacheable(key = "#id", value = "persons")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    @CacheEvict(key = "#id", value = "persons")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.removePersonById(id);
    }

    @PutMapping(path = "{id}")
    @CachePut(key = "#id", value = "persons")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person person) {
        personService.updatePerson(id, person);
    }

}

