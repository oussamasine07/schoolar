package org.schoolservice.service;

import org.schoolservice.dto.request.AcademicYearValidationDTO;
import org.schoolservice.exception.NotFoundException;
import org.schoolservice.model.AcademicYear;
import org.schoolservice.repo.AcademicYearRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AcademicYearService {
    private final AcademicYearRepo academicYearRepo;

    public AcademicYearService(AcademicYearRepo academicYearRepo) {
        this.academicYearRepo = academicYearRepo;
    }

    public ResponseEntity<List<AcademicYear>> listAllAcademicYears () {
        List<AcademicYear> academicYears = academicYearRepo.findAll();

        return new ResponseEntity<>(academicYears, HttpStatus.OK);
    }

    public ResponseEntity<AcademicYear> showAcademicYear ( Long academicYearId ) {
        AcademicYear academicYear = academicYearRepo.findById( academicYearId )
                .orElseThrow(() -> new NotFoundException("unfound year"));

        return new ResponseEntity<>(academicYear, HttpStatus.OK);
    }

    public ResponseEntity<AcademicYear> createNewAcademicYear (
            AcademicYearValidationDTO academicYearValidationDTO
    ) {
        AcademicYear academicYear = new AcademicYear();

        academicYear.setStart(academicYearValidationDTO.start());
        academicYear.setEnd(academicYearValidationDTO.end());

        AcademicYear savedAcademicYear = academicYearRepo.save( academicYear );
        return new ResponseEntity<>( savedAcademicYear, HttpStatus.OK );
    }

    public ResponseEntity<AcademicYear> updateAcademicYear (
            Long academicYearId,
            AcademicYearValidationDTO academicYearValidationDTO
    ) {

        AcademicYear academicYear = academicYearRepo.findById( academicYearId )
                .orElseThrow(() -> new NotFoundException("unfound year"));

        academicYear.setStart(academicYearValidationDTO.start());
        academicYear.setEnd(academicYearValidationDTO.end());

        AcademicYear savedAcademicYear = academicYearRepo.save( academicYear );
        return new ResponseEntity<>( savedAcademicYear, HttpStatus.OK );
    }

    // todo make soft delete in the future
    public ResponseEntity<Map<String, Object>> deleteAcademicYear (Long academicYearId) {

        AcademicYear academicYear = academicYearRepo.findById( academicYearId )
                .orElseThrow(() -> new NotFoundException("unfound year"));

        Map<String, Object> success = new HashMap<>();
        success.put("status", 200);
        success.put("classroom", academicYear);

        academicYearRepo.deleteById( academicYearId );

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    public ResponseEntity<AcademicYear> closeAcademicYear ( Long academicYearId ) {
        AcademicYear academicYear = academicYearRepo.findById( academicYearId )
                .orElseThrow(() -> new NotFoundException("unfound year"));

        academicYear.setClosed( true );

        AcademicYear savedAcademicYear = academicYearRepo.save( academicYear );
        return new ResponseEntity<>( savedAcademicYear, HttpStatus.OK );
    }

}

























