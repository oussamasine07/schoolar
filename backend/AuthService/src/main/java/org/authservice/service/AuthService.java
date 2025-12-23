package org.authservice.service;


import org.authservice.dto.request.LoginValidationDTO;
import org.authservice.dto.response.AuthUserDTO;
import org.authservice.exception.PasswordIncorrectException;
import org.authservice.model.AppUser;
import org.authservice.repo.AppUserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final AppUserRepo appUserRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService (
            final AuthenticationManager authenticationManager,
            final AppUserRepo appUserRepo,
            final JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.appUserRepo = appUserRepo;
        this.jwtService = jwtService;
    }

    public ResponseEntity<Map<String, String>> appUserLogin (LoginValidationDTO loginValidationDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginValidationDTO.email(),
                            loginValidationDTO.password()
                    )
            );


            if (authentication.isAuthenticated()) {

                AppUser authenticatedUser = appUserRepo.findAppUserByEmail( loginValidationDTO.email() ).orElseThrow();
                String token = jwtService.generateJwtToken( authenticatedUser );

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

            throw new PasswordIncorrectException("invalid credantials");
        }
        catch (AuthenticationException e) {
            throw new PasswordIncorrectException("invalid credentials");
        }

    }


    public AuthUserDTO getAuthenticatedUser ( String email ) {
        AppUser authenticatedUser = appUserRepo.findAppUserByEmail( email ).orElseThrow();

        return new AuthUserDTO(
            authenticatedUser.getId(),
                authenticatedUser.getFirstName(),
                authenticatedUser.getFirstName(),
                authenticatedUser.getEmail(),
                authenticatedUser.getAuthorities()
        );
    }
}




















