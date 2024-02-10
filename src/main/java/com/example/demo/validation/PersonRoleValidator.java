package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PersonRoleValidator implements ConstraintValidator<ValidatePersonRole, String> {
    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roleList = List.of("Admin", "User");
        return roleList.contains(role);
    }
}
