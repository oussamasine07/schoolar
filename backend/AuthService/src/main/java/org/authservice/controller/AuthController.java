package org.authservice.controller;

import org.authservice.dto.request.LoginValidationDTO;
import org.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController (
            final AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/app/login")
    public ResponseEntity<Map<String, String>> login (@RequestBody LoginValidationDTO loginValidationDTO) {
        return authService.appUserLogin( loginValidationDTO );
    }

}
