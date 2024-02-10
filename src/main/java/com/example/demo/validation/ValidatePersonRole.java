package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PersonRoleValidator.class)
public @interface ValidatePersonRole {

    String message() default "Invalid person role: It must be either Admin OR User";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
