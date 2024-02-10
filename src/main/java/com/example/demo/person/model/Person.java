package com.example.demo.person.model;

import com.example.demo.validation.ValidatePersonRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.internal.util.Cloneable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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