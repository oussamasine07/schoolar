package org.schoolservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LevelOfEducationValidationDTO (

        @NotBlank(message = "name field is required")
        String name,

        @NotBlank(message = "educational stage is required")
        String educationalStage

) {}
