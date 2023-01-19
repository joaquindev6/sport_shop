package com.jfarro.app.validators.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NamesConstraintValidator implements ConstraintValidator<NamesRegex, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("([a-zA-Z]+\\s?){1,3}");
    }
}
