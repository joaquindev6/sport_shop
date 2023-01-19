package com.jfarro.app.validators.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordRegex, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.matches("^[\\w$%&/#=?¿!¡.,;|*']{5,30}$")) {
            if (s.matches("^[a-zA-Z]{5,30}$")) {
                return false;
            } else if (s.matches("^[a-zA-Z0-9]{5,30}$")) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
