package org.schoolservice.controller;

import jakarta.validation.Valid;
import org.schoolservice.dto.request.SchoolValidationDTO;
import org.schoolservice.model.School;
import org.schoolservice.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController (
            final SchoolService schoolService
    ) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<School> createNewSchool (
            @Valid @RequestBody SchoolValidationDTO schoolValidationDTO,
            @RequestHeader("Authorization") String token
    ) {

        return schoolService.createNewSchool( schoolValidationDTO, token );
    }

}
