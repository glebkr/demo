package com.example.demo.person.model;

import com.example.demo.validation.ValidatePersonRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @JsonProperty("id")
    private UUID id;

    @NotBlank(message = "name shouldn't be null or empty")
    @JsonProperty("name")
    private String name;

    @Min(value = 5000, message = "min")
    @Max(value = 10000, message = "max")
    @JsonProperty("salary")
    private Integer salary;

    @JsonProperty("role")
    @NotBlank(message = "role shouldn't be null or empty")
    @ValidatePersonRole
    private String role;

    /*    @JsonProperty("role")
    @Enumerated(EnumType.STRING)
    private String role;*/

}