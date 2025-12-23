package org.authservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.authservice.model.AppUser;
import org.authservice.service.AppUserService;

import java.util.Optional;

public class IsEmailAlreadyExistsValidator implements ConstraintValidator<IsEmailAlreadyExists, String> {

    private final AppUserService appUserService;

    public IsEmailAlreadyExistsValidator (
            final AppUserService appUserService
    ) {
        this.appUserService = appUserService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<AppUser> found = appUserService.findUserByEmail( email );
        return found.isEmpty();
    }
}