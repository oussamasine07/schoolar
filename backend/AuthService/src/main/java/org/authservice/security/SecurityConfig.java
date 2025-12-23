package org.authservice.security;


import org.authservice.exception.CustomAccessDeniedHandler;
import org.authservice.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig (
            final JwtFilter jwtFilter,
            final CustomAccessDeniedHandler customAccessDeniedHandler
    ) {

        this.jwtFilter = jwtFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;

    }

    @Bean
    public SecurityFilterChain filterChain (
            HttpSecurity httpSecurity,
            AuthenticationProvider authenticationProvider
    ) throws Exception {

        System.out.println("JWT FILTER: ");

        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(
                                    "/api/v1/auth/app/login",
                                    "/api/v1/register/register-owner"
                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .exceptionHandling(ex ->
                    ex.accessDeniedHandler( customAccessDeniedHandler )
                )
                .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider( authenticationProvider )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}



























