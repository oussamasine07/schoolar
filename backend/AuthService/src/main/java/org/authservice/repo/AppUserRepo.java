package org.authservice.repo;

import org.authservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByEmail(String email);
            

}
