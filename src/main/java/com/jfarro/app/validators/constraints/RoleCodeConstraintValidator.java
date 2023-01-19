package com.jfarro.app.validators.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleCodeConstraintValidator implements ConstraintValidator<RoleCodeRegex, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[A-Z]+_[A-Z0-9]+");
    }
}
