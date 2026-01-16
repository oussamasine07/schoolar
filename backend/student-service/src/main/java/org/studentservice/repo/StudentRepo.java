package org.studentservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.studentservice.model.Student;



public interface StudentRepo extends JpaRepository<Student, Long> {

    boolean existsByRef(String ref);
}
