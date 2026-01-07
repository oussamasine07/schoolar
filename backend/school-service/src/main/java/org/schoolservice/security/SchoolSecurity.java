package org.schoolservice.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.schoolservice.repo.SchoolRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SchoolSecurity {

    private final SchoolRepo schoolRepo;
    public SchoolSecurity ( final SchoolRepo schoolRepo ) {
        this.schoolRepo = schoolRepo;
    }

    public boolean isSchoolOwner(Long schoolId, Authentication authentication) {

        Claims claims = (Claims) authentication.getPrincipal();

        Long loggedinUser = claims.get("id", Long.class);

        return schoolRepo.existsByIdAndOwnerId(schoolId, loggedinUser);
    }

}
