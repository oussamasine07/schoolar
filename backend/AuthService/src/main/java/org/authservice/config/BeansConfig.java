package org.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfig {


    @Bean
    public AuthenticationProvider authenticationProvider (
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                // get user from authentication
                String username = authentication.getName();
                String password = authentication.getCredentials().toString();

                // get user from userDetailsService
                UserDetails user = userDetailsService.loadUserByUsername(username);

                // compare passwords
                if (!passwordEncoder.matches(password, user.getPassword())) {
                    throw new BadCredentialsException("password incorrect");
                }

                // authenticate
                return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom( authentication );
            }
        };
    }

    @Bean
    PasswordEncoder encoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
