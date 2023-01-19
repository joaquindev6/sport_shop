package com.jfarro.app.validators.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NroDocuConstraintValidator implements ConstraintValidator<NroDocumentRegex, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[0-9]{8,12}");
    }
}
