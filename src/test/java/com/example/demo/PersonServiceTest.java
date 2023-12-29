package com.example.demo;

import com.example.demo.person.model.Person;
import com.example.demo.person.repository.PersonRepository;
import com.example.demo.person.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;

    @Test
    void getAllPeople() {
        Person person1 = new Person(UUID.randomUUID(), "Gleb");
        Person person2 = new Person(UUID.randomUUID(), "Raichi");
        Mockito.when(personRepository.getAllPersons()).thenReturn(List.of(person1, person2));

        List<Person> list = personService.getAllPersons();
        Assertions.assertEquals(2, list.size());

        Mockito.verify(personRepository, Mockito.times(1)).getAllPersons();
    }

    @Test
    void sum() {
        int result = 1 + 2;
        Assertions.assertEquals(3, result);
    }

    @Test
    void test_bool_assertions() {
        Assertions.assertTrue(true);
        Assertions.assertFalse(false);
    }

    @Test
    void divide_by_zero() {
            Assertions.assertThrows(ArithmeticException.class, () -> { int a = 10 / 0; } );
    }

}
