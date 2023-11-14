package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    public static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,  person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
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
                        DB.set(indexOfPerson, new Person(id, person.getName()));
                        return 1;
                    }
                    return 0;
                });
    }

    @Override
    public void getDiscountByPromo(String shdes, String promoCode) {
        //call database request
    }

}
