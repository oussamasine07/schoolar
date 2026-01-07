package org.schoolservice.controller;

import jakarta.validation.Valid;
import org.schoolservice.dto.request.AcademicYearValidationDTO;
import org.schoolservice.model.AcademicYear;
import org.schoolservice.service.AcademicYearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/academic-year")
public class AcademicYearController {

    private final AcademicYearService academicYearService;

    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    @GetMapping
    public ResponseEntity<List<AcademicYear>> listAllAcademicYears () {
        return academicYearService.listAllAcademicYears();
    }

    @GetMapping("/{academicYearId}")
    public ResponseEntity<AcademicYear> showAcademicYear (
            @PathVariable("academicYearId") Long academicYearId
    ) {
        return academicYearService.showAcademicYear( academicYearId );
    }

    @PostMapping
    public ResponseEntity<AcademicYear> createNewAcademicYear (
            @Valid @RequestBody AcademicYearValidationDTO academicYearValidationDTO
    ) {
        System.out.println(academicYearValidationDTO.toString());
        return academicYearService.createNewAcademicYear( academicYearValidationDTO );
    }

    @PutMapping("/{academicYearId}")
    public ResponseEntity<AcademicYear> updateAcademicYear (
            @PathVariable("academicYearId") Long academicYearId,
            @Valid @RequestBody AcademicYearValidationDTO academicYearValidationDTO
    ) {
        return academicYearService.updateAcademicYear( academicYearId, academicYearValidationDTO );
    }

    @DeleteMapping("/{academicYearId}")
    public ResponseEntity<Map<String, Object>> deleteAcademicYear (
            @PathVariable("academicYearId") Long academicYearId
    ) {
        return academicYearService.deleteAcademicYear( academicYearId );
    }

    @PutMapping("/close-year/{academicYearId}")
    public ResponseEntity<AcademicYear> closeAcademicYear ( @PathVariable("academicYearId") Long academicYearId ) {
        return academicYearService.closeAcademicYear( academicYearId );
    }


}























