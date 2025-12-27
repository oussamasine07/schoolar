package org.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
public class BeansConfig {

    @Bean
    public AuthenticationWebFilter jwtAuthenticationWebFilter (
            JwtReactiveAuthenticationManager authManager,
            JwtServerAuthenticationConverter authenticationConverter
    ) {

        AuthenticationWebFilter filter = new AuthenticationWebFilter( authManager );
        filter.setServerAuthenticationConverter( authenticationConverter );

        return filter;
    }

}
