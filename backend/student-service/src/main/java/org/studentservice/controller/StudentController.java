package org.studentservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.studentservice.dto.request.StudentValidationDTO;
import org.studentservice.model.Student;
import org.studentservice.service.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> listAllStudents () {
        return studentService.listAllStudents();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById (@PathVariable("studentId") Long studentId) {
        return studentService.getStudentById( studentId );
    }

    @PostMapping
    public ResponseEntity<Student> createStudent ( @Valid @RequestBody StudentValidationDTO studentValidationDTO) {
        return studentService.createStudent( studentValidationDTO );
    }

    @PutMapping("{studentId}")
    public ResponseEntity<Student> updateStudent (
            @Valid @RequestBody StudentValidationDTO studentValidationDTO,
            @PathVariable("studentId") Long studentId
    ) {
        return studentService.updateStudent(studentValidationDTO, studentId);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Map<String, Object>> deleteStudent (@PathVariable("studentId") Long studentId) {
        return studentService.deleteStudent( studentId );
    }

}























