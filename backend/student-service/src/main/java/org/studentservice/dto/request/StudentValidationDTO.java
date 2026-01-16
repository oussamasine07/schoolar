package org.studentservice.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record StudentValidationDTO (
    @NotBlank(message = "firstname is required")
    String firstName,

    @NotBlank(message = "lastname is required")
    String lastName,

    @NotNull(message = "date of birth is required")
    @Past(message = "dateOfBirth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth,

    @NotNull(message = "city of birth is required")
    String cityOfBirth,

    @NotBlank(message = "city of living is required")
    String cityOfLiving,

    @NotBlank(message = "student address is required")
    String address

) {}
