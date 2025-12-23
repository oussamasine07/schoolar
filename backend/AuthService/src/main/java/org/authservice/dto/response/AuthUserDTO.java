package org.authservice.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record AuthUserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Collection<? extends GrantedAuthority> authorities
) {
}
