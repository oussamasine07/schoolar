package org.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginValidationDTO(
        @NotBlank(message = "email is required")
        String email,

        @NotBlank(message = "password is required")
        String password
) {
}
