package org.authservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class IsPasswordConfirmedValidator implements ConstraintValidator<IsPasswordConfirmed, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        try {
            BeanWrapperImpl wrapper = new BeanWrapperImpl(obj);
            String password = (String) wrapper.getPropertyValue("password");
            String confirmPassword = (String) wrapper.getPropertyValue("confirmPassword");

            if (password == null || confirmPassword == null || password.isBlank() || confirmPassword.isBlank()) {
                return true;
            }

            boolean isMatch = password.equals(confirmPassword);
            if (!isMatch) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Passwords do not match")
                        .addPropertyNode("confirmPassword") // Associate error with confirmPassword field
                        .addConstraintViolation();
            }

            return isMatch;
        }
        catch (Exception e) {
            return true;
        }
    }
}