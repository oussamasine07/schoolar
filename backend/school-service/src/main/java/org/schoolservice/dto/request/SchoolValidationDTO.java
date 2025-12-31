package org.schoolservice.dto.request;
import jakarta.validation.constraints.NotBlank;

public record SchoolValidationDTO(
        @NotBlank(message = "school name is required")
        String schoolName,
        @NotBlank(message = "school email is required")
        String email,
        @NotBlank(message = "school phone is required")
        String phone,
        @NotBlank(message = "school address is required")
        String address,
        String city,
        int taxId,
        int professionalTax,
        int cnssAffiliation,
        int commercialRegister,
        int commonBusinessIdentifier
) {
}
