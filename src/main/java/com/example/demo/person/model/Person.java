package com.example.demo.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;
import javax.persistence.Entity;

public record Person(UUID id, @NotBlank String name) {

    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
