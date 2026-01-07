package org.schoolservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AcademicYearValidationDTO (
        @NotNull(message = "start date is required")
        LocalDate start,
        @NotNull(message = "start date is required")
        LocalDate end
) {}
