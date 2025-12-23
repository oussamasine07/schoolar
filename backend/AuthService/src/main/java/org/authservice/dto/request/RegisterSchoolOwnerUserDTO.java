package org.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.authservice.validation.IsEmailAlreadyExists;
import org.authservice.validation.IsPasswordConfirmed;

@IsPasswordConfirmed
public record RegisterSchoolOwnerUserDTO(
    @NotBlank(message = "fistname is required")
    String firstName,

    @NotBlank(message = "lastname is required")
    String lastName,

    @NotBlank(message = "email is required")
    @Email
    @IsEmailAlreadyExists
    String email,

    @NotBlank(message = "password is required")
    String password,

    @NotBlank(message = "passord confirmation is required")
    String confirmPassword

) {
}
