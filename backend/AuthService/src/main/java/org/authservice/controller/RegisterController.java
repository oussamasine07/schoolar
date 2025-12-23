package org.authservice.controller;

import jakarta.validation.Valid;
import org.authservice.dto.request.RegisterSchoolOwnerUserDTO;
import org.authservice.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    private final RegisterService registerService;
    public RegisterController (
            final RegisterService registerService
    ) {
        this.registerService = registerService;
    }

    @PostMapping("/register-owner")
    public ResponseEntity<Map<String, String>> registerSchoolOwner (@Valid @RequestBody RegisterSchoolOwnerUserDTO registerSchoolOwnerUserDTO) {
        return registerService.registerSchoolOwner( registerSchoolOwnerUserDTO );
    }
}
