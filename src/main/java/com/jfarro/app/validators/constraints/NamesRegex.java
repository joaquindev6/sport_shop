package com.jfarro.app.validators.constraints;

import com.jfarro.app.validators.constraints.NamesConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NamesConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface NamesRegex {
    String message() default "{NamesRegex.names}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
