package org.schoolservice.service;

import org.schoolservice.dto.request.LevelOfEducationValidationDTO;
import org.schoolservice.exception.NotFoundException;
import org.schoolservice.model.LevelOfEducation;
import org.schoolservice.repo.LevelOfEducationRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LevelOfEducationService {

    private final LevelOfEducationRepo levelOfEducationRepo;


    public LevelOfEducationService(
            LevelOfEducationRepo levelOfEducationRepo
    ) {
        this.levelOfEducationRepo = levelOfEducationRepo;
    }

    public ResponseEntity<List<LevelOfEducation>> listAllLevelsOfEducation () {
        List<LevelOfEducation> levelsOfEducation = levelOfEducationRepo.findAll();
        return new ResponseEntity<>(levelsOfEducation, HttpStatus.OK);
    }

    public ResponseEntity<LevelOfEducation> showLevelOfEducation (Long levelOfEducationId) {
        LevelOfEducation levelOfEducation = levelOfEducationRepo.findById(levelOfEducationId)
                .orElseThrow(() -> new NotFoundException("unfound level of education"));

        return new ResponseEntity<>(levelOfEducation, HttpStatus.OK);
    }

    public ResponseEntity<LevelOfEducation> createLevelOfEducation (
            LevelOfEducationValidationDTO levelOfEducationValidationDTO
    ) {
        LevelOfEducation levelOfEducation = new LevelOfEducation();
        levelOfEducation.setName( levelOfEducationValidationDTO.name());
        levelOfEducation.setEducationalStage(levelOfEducationValidationDTO.educationalStage());

        LevelOfEducation savedLevelOfEducation = levelOfEducationRepo.save( levelOfEducation );

        return new ResponseEntity<>(savedLevelOfEducation, HttpStatus.OK);
    }

    public ResponseEntity<LevelOfEducation> updateLevelOFEducation (
            Long levelOfEducationId,
            LevelOfEducationValidationDTO levelOfEducationValidationDTO
    ) {
        LevelOfEducation levelOfEducation = levelOfEducationRepo.findById(levelOfEducationId)
                .orElseThrow(() -> new NotFoundException("unfound level of education"));

        levelOfEducation.setName( levelOfEducationValidationDTO.name());
        levelOfEducation.setEducationalStage(levelOfEducationValidationDTO.educationalStage());

        LevelOfEducation savedLevelOfEducation = levelOfEducationRepo.save( levelOfEducation );

        return new ResponseEntity<>(savedLevelOfEducation, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> deleteLevelOfEducation ( Long levelOfEducationId ) {
        LevelOfEducation levelOfEducation = levelOfEducationRepo.findById(levelOfEducationId)
                .orElseThrow(() -> new NotFoundException("unfound level of education"));

        Map<String, Object> success = new HashMap<>();
        success.put("status", 200);
        success.put("classroom", levelOfEducation);

        levelOfEducationRepo.deleteById( levelOfEducationId );

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

}
































