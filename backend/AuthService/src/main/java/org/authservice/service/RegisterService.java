package org.authservice.service;

import org.authservice.dto.request.RegisterSchoolOwnerUserDTO;
import org.authservice.model.AppUser;
import org.authservice.model.AppUserRole;
import org.authservice.repo.AppUserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepo appUserRepo;
    private final JwtService jwtService;

    public RegisterService (
            final PasswordEncoder passwordEncoder,
            final AppUserRepo appUserRepo,
            final JwtService jwtService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepo = appUserRepo;
        this.jwtService = jwtService;
    }

    public ResponseEntity<Map<String, String>> registerSchoolOwner (RegisterSchoolOwnerUserDTO registerSchoolOwnerUserDTO) {

        AppUser user = new AppUser();
        user.setEmail(registerSchoolOwnerUserDTO.email());
        user.setFirstName(registerSchoolOwnerUserDTO.firstName());
        user.setLastName(registerSchoolOwnerUserDTO.lastName());
        user.setRole(AppUserRole.SCHOOL_OWNER);
        user.setPassword(passwordEncoder.encode(registerSchoolOwnerUserDTO.password()));

        AppUser savedUser = appUserRepo.save( user );
        String token = jwtService.generateJwtToken( savedUser );
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}



