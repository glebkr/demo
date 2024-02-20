package com.example.demo.person.api;

import com.example.demo.person.model.Person;
import com.example.demo.person.service.PersonService;
import com.example.demo.rabbitmq.Receiver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/api/v1/person")
@RestController
@EnableCaching
@Slf4j
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    @PostMapping
    @CachePut(key = "#person.id", value = "persons")
    public void addPerson(@Valid @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    @Cacheable(value = "persons")
    public List<Person> getAllPeople() throws Exception {
//        rabbitTemplate.sendAndReceive(RabbitConfig.topicExchangeName, "foo.bar.baz", new Message("Hellooo".getBytes()));
//        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        return personService.getAllPersons();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"offset", "limit"})
    public Page<Person> getPageWithPeople(@RequestParam(value = "offset") Integer offset, @RequestParam(value = "limit") Integer limit) {
        return personService.getPageWithPeople(PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "name")));
    }

    @GetMapping(path = "{id}")
    @Cacheable(key = "#id", value = "persons")
    @Operation(summary = "Get a user by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)) } ),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User is not found",
                    content = @Content)})
    public Person getPersonById(@PathVariable("id") UUID id) {
        Person person = personService.getPersonById(id).orElse(null);
        log.info(person.getId().toString());
        return person;
    }

    @DeleteMapping(path = "{id}")
    @CacheEvict(key = "#id", value = "persons")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.removePersonById(id);
    }

    @PutMapping(path = "{id}")
    @CachePut(key = "#id", value = "persons")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @RequestBody Person person) {
        personService.updatePerson(id, person);
    }

}

