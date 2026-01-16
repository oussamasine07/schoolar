package org.studentservice.utils;

import org.springframework.stereotype.Component;
import org.studentservice.repo.StudentRepo;

import java.util.UUID;

@Component
public class ReferenceGeneratorUtil {

    private final StudentRepo studentRepo;

    public ReferenceGeneratorUtil(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public String generateStudentRef () {

        String ref = "STD-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        if (studentRepo.existsByRef( ref )) generateStudentRef();

        return ref;
    }

}
