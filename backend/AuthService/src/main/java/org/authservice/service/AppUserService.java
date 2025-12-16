package org.authservice.service;

import org.authservice.model.AppUser;
import org.authservice.repo.AppUserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepo appUserRepo;

    public AppUserService (
            final AppUserRepo appUserRepo
    ) {
        this.appUserRepo = appUserRepo;
    }

    public Optional<AppUser> findUserByEmail (String email) {
        return appUserRepo.findAppUserByEmail( email );
    }

}
