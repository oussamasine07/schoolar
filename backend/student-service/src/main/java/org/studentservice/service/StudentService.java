package org.studentservice.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.studentservice.dto.request.StudentValidationDTO;
import org.studentservice.exception.NotFoundException;
import org.studentservice.model.Student;
import org.studentservice.repo.StudentRepo;
import org.studentservice.utils.ReferenceGeneratorUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final ReferenceGeneratorUtil referenceGeneratorUtil;

    public StudentService(
            StudentRepo studentRepo,
            ReferenceGeneratorUtil referenceGeneratorUtil
    ) {
        this.studentRepo = studentRepo;
        this.referenceGeneratorUtil = referenceGeneratorUtil;
    }

    public ResponseEntity<List<Student>> listAllStudents () {
        List<Student> students = studentRepo.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public ResponseEntity<Student> getStudentById (Long studentId) {
        Student student = studentRepo.findById( studentId )
                .orElseThrow(() -> new NotFoundException("student not found"));

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    public ResponseEntity<Student> createStudent (StudentValidationDTO studentValidationDTO) {

        Student newStudent = new Student();
        newStudent.setFirstName( studentValidationDTO.firstName() );
        newStudent.setLastName( studentValidationDTO.lastName() );
        newStudent.setDateOfBirth( studentValidationDTO.dateOfBirth() );
        newStudent.setCityOfBirth( studentValidationDTO.cityOfBirth() );
        newStudent.setCityOfLiving( studentValidationDTO.cityOfLiving() );
        newStudent.setAddress( studentValidationDTO.address() );

        // generate studentRef
        String ref = referenceGeneratorUtil.generateStudentRef();
        newStudent.setRef( ref );

        Student savedStudent = studentRepo.save( newStudent );

        return new ResponseEntity<>(savedStudent, HttpStatus.OK);
    }

    public ResponseEntity<Student> updateStudent (StudentValidationDTO studentValidationDTO, Long studentId) {
        Student student = studentRepo.findById( studentId )
                .orElseThrow(() -> new NotFoundException("student not found"));

        student.setFirstName( studentValidationDTO.firstName() );
        student.setLastName( studentValidationDTO.lastName() );
        student.setDateOfBirth( studentValidationDTO.dateOfBirth() );
        student.setCityOfBirth( studentValidationDTO.cityOfBirth() );
        student.setCityOfLiving( studentValidationDTO.cityOfLiving() );
        student.setAddress( studentValidationDTO.address() );

        // generate studentRef
        String ref = referenceGeneratorUtil.generateStudentRef();
        student.setRef( ref );

        Student savedStudent = studentRepo.save( student );

        return new ResponseEntity<>(savedStudent, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> deleteStudent (Long studentId) {

        Student student = studentRepo.findById( studentId )
                .orElseThrow(() -> new NotFoundException("student not found"));

        Map<String, Object> success = new HashMap<>();
        success.put("status", 200);
        success.put("student", student);

        studentRepo.deleteById( studentId );

        return new ResponseEntity<>(success, HttpStatus.OK);
    }


}





















