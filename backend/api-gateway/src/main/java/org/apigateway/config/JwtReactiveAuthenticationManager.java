package org.apigateway.config;

import org.apigateway.service.JwtService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

@Configuration
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtService jwtService;

    public JwtReactiveAuthenticationManager ( final JwtService jwtService ) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        if (!jwtService.validateToken( token )) {
            return Mono.error(new BadCredentialsException("invalid credentials"));
        }

        String username = jwtService.extractUsername( token );

        return Mono.just( new UsernamePasswordAuthenticationToken(username, null, authentication.getAuthorities()));
    }
}
