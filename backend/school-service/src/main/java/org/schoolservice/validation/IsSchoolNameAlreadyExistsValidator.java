package org.schoolservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.schoolservice.model.School;
import org.schoolservice.repo.SchoolRepo;

import java.util.Optional;

public class IsSchoolNameAlreadyExistsValidator implements ConstraintValidator<IsSchoolNameAlreadyExists, String> {

    private final SchoolRepo schoolRepo;
    public IsSchoolNameAlreadyExistsValidator (
            final SchoolRepo schoolRepo
    ) {
        this.schoolRepo = schoolRepo;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Optional<School> foundSchool = schoolRepo.findSchoolBySchoolName( s );
        return foundSchool.isEmpty();
    }
}






















