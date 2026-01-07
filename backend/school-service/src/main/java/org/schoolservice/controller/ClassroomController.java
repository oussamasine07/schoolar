package org.schoolservice.controller;

import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Delete;
import org.schoolservice.dto.request.ClassroomValidationDTO;
import org.schoolservice.model.Classroom;
import org.schoolservice.service.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;
    public ClassroomController ( final ClassroomService classroomService ) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> listClassrooms () {
        return classroomService.listClassrooms();
    }

    @GetMapping("/{classroomId}")
    public ResponseEntity<Classroom> showClassroom (
            @PathVariable("classroomId") Long classroomId
    ) {
        return classroomService.showClassroom( classroomId );
    }

    @PostMapping
    public ResponseEntity<Classroom> createClassroom (
            @Valid @RequestBody ClassroomValidationDTO classroomValidationDTO
    ) {
        return classroomService.createClassroom( classroomValidationDTO );
    }

    @PutMapping("/{classroomId}")
    ResponseEntity<Classroom> updateClassroom (
            @Valid @RequestBody ClassroomValidationDTO classroomValidationDTO,
            @PathVariable("classroomId")Long classroomId
    ) {
        return classroomService.updateClassroom( classroomValidationDTO, classroomId);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<Map<String, Object>> deleteClassroom (
            @PathVariable("classroomId") Long classroomId
    ) {
        return classroomService.deleteClassroom( classroomId );
    }

}















