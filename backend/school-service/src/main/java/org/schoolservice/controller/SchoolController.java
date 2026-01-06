package org.schoolservice.controller;

import jakarta.validation.Valid;
import org.schoolservice.dto.request.SchoolValidationDTO;
import org.schoolservice.model.School;
import org.schoolservice.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController (
            final SchoolService schoolService
    ) {
        this.schoolService = schoolService;
    }

    @GetMapping("/owner-schools")
    public ResponseEntity<List<School>> listOwnerSchools (@RequestHeader("Authorization") String token) {
        return schoolService.listOwnerSchools( token );
    }

    @PreAuthorize("@schoolSecurity.isSchoolOwner(#schoolId, authentication)")
    @GetMapping("/school/{schoolId}")
    public ResponseEntity<School> showSchoolByOwnerId (
            @PathVariable("schoolId") Long schoolId
    ) {
        return schoolService.showSchoolByOwnerId(schoolId);
    }

    // TODO add roles (admin, school_owner)
    @PostMapping
    public ResponseEntity<School> createNewSchool (
            @Valid @RequestBody SchoolValidationDTO schoolValidationDTO,
            @RequestHeader("Authorization") String token
    ) {

        return schoolService.createNewSchool( schoolValidationDTO, token );
    }



}
