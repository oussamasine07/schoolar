package org.schoolservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ClassroomValidationDTO (
        @NotBlank(message = "classroom name is required")
        String name
) {
}
