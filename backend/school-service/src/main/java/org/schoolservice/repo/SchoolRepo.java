package org.schoolservice.repo;

import org.schoolservice.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepo extends JpaRepository<School, Long> {

}
