package com.jfarro.app.validators.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleCodeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface RoleCodeRegex {
    String message() default "{RoleCodeRegex.code}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
