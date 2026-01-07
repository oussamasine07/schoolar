package org.schoolservice.controller;

import jakarta.validation.Valid;
import org.schoolservice.dto.request.LevelOfEducationValidationDTO;
import org.schoolservice.model.LevelOfEducation;
import org.schoolservice.service.LevelOfEducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/level-of-education")
public class LevelOfEducationController {

    private final LevelOfEducationService levelOfEducationService;

    public LevelOfEducationController(
            LevelOfEducationService levelOfEducationService
    ) {
        this.levelOfEducationService = levelOfEducationService;
    }

    @GetMapping
    public ResponseEntity<List<LevelOfEducation>> listAllLevelsOfEducation () {
        return levelOfEducationService.listAllLevelsOfEducation();
    }

    @GetMapping("/{levelOfEducationId}")
    public ResponseEntity<LevelOfEducation> showLevelOfEducation (@PathVariable("levelOfEducationId") Long levelOfEducationId) {
        return levelOfEducationService.showLevelOfEducation( levelOfEducationId );
    }

    @PostMapping
    public ResponseEntity<LevelOfEducation> createLevelOfEducation (
            @Valid @RequestBody LevelOfEducationValidationDTO levelOfEducationValidationDTO
    ) {

        return levelOfEducationService.createLevelOfEducation( levelOfEducationValidationDTO );
    }

    @PutMapping("/{levelOfEducationId}")
    public ResponseEntity<LevelOfEducation> updateLevelOFEducation (
            @PathVariable("levelOfEducationId") Long levelOfEducationId,
            @Valid @RequestBody LevelOfEducationValidationDTO levelOfEducationValidationDTO
    ) {
        return levelOfEducationService.updateLevelOFEducation(levelOfEducationId, levelOfEducationValidationDTO);
    }

    @DeleteMapping("/{levelOfEducationId}")
    public ResponseEntity<Map<String, Object>> deleteLevelOfEducation ( @PathVariable("levelOfEducationId") Long levelOfEducationId ) {
        return levelOfEducationService.deleteLevelOfEducation( levelOfEducationId );
    }

}


























