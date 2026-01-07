package org.schoolservice.service;

import org.schoolservice.dto.request.ClassroomValidationDTO;
import org.schoolservice.exception.NotFoundException;
import org.schoolservice.model.Classroom;
import org.schoolservice.repo.ClassroomRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassroomService {

    private final ClassroomRepo classroomRepo;
    public ClassroomService ( final ClassroomRepo classroomRepo ) {
        this.classroomRepo = classroomRepo;
    }

    public ResponseEntity<List<Classroom>> listClassrooms () {
        List<Classroom> classrooms = classroomRepo.findAll();
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    public ResponseEntity<Classroom> showClassroom ( Long classroomId ) {

        Classroom foundClassroom = classroomRepo.findById( classroomId )
                .orElseThrow(() -> new NotFoundException("unfound classroom"));

        return new ResponseEntity<>( foundClassroom, HttpStatus.OK );
    }

    public ResponseEntity<Classroom> createClassroom (ClassroomValidationDTO classroomValidationDTO) {
        Classroom classroom = new Classroom();
        classroom.setName(classroomValidationDTO.name());
        Classroom savedClassroom = classroomRepo.save( classroom );
        return new ResponseEntity<>( savedClassroom, HttpStatus.OK );
    }

    public ResponseEntity<Classroom> updateClassroom (ClassroomValidationDTO classroomValidationDTO, Long classroomId ) {
        Classroom foundClassroom = classroomRepo.findById( classroomId )
                .orElseThrow(() -> new NotFoundException("unfound classroom"));
        foundClassroom.setName(classroomValidationDTO.name());
        return new ResponseEntity<>(classroomRepo.save( foundClassroom ), HttpStatus.OK );
    }

    public ResponseEntity<Map<String, Object>> deleteClassroom ( Long classroomId ) {
        Classroom foundClassroom = classroomRepo.findById( classroomId )
                .orElseThrow(() -> new NotFoundException("unfound classroom"));

        Map<String, Object> success = new HashMap<>();
        success.put("status", 200);
        success.put("classroom", foundClassroom);

        classroomRepo.deleteById( classroomId );

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

}
























