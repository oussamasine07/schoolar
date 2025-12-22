package org.authservice.service;

import org.authservice.model.AppUser;
import org.authservice.repo.AppUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final AppUserRepo appUserRepo;

    public UserDetailsServiceImpl (AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // get the user from database
        AppUser appUser = appUserRepo.findAppUserByEmail( email ).orElseThrow();

        if (appUser == null) {
            logger.info("user not found");
            throw new UsernameNotFoundException("this user does not exist");
        }
        return appUser;
    }
}
