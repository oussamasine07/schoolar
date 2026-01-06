package org.schoolservice.repo;

import org.schoolservice.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepo extends JpaRepository<School, Long> {
    Optional<School> findSchoolBySchoolName(String schoolName);

    List<School> findSchoolsByOwnerId(Long ownerId);

    boolean existsByIdAndOwnerId(Long id, Long ownerId);
}
