package com.example.demo.person.dao;

import com.example.demo.person.model.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
@Primary
public class FakePersonDataAccessService implements PersonDao {

    public static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        var newPerson = Person.builder()
                .id(id)
                .name(person.getName())
                .role("Admin")
                .salary(6000).build();
        DB.add(newPerson);
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        System.out.println("JIEGRIEGRIGER");
        return DB;
    }

    @Override
    public void deletePersonById(UUID id) {
        var personMaybe = getPersonById(id);
        personMaybe.ifPresent(person -> DB.remove(person));
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public void updatePerson(UUID id, Person person) {
        getPersonById(id)
                .map(p -> {
                    int indexOfPerson = DB.indexOf(p);
                    if (indexOfPerson >= 0) {
                        var newPerson = Person.builder()
                                .id(id)
                                .name(person.getName())
                                .role("Admin")
                                .salary(6000).build();
                        DB.set(indexOfPerson, newPerson);
                        return 1;
                    }
                    return 0;
                });
    }

}
