package com.jfarro.app.validators.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LastNamesConstraintValidator implements ConstraintValidator<LastNamesRegex, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[A-Za-z]+\\s?");
    }
}
