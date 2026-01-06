package org.schoolservice.repo;

import org.schoolservice.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepo extends JpaRepository<Classroom, Long> {

}
