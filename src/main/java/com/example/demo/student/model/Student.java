package com.example.demo.student.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record Student (@JsonProperty("id") UUID id, @JsonProperty("name") String name) {

}
