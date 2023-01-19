package com.jfarro.app.validators.constraints;

import com.jfarro.app.validators.constraints.LastNamesConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LastNamesConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface LastNamesRegex {
    String message() default "{LastNamesRegex.names}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
