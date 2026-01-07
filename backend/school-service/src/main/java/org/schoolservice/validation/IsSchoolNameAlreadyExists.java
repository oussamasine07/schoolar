package org.schoolservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsSchoolNameAlreadyExistsValidator.class )
public @interface IsSchoolNameAlreadyExists {

    String message() default "this school name is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
