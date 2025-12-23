package org.authservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsEmailAlreadyExistsValidator.class)
public @interface IsEmailAlreadyExists {

    String message() default "this email is already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
