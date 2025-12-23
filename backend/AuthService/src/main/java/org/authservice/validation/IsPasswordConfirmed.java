package org.authservice.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsPasswordConfirmedValidator.class)
public @interface IsPasswordConfirmed {
    String message() default "please confirm your password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
