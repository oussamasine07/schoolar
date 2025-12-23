package org.authservice.factory;

import org.authservice.model.AppUser;
import org.authservice.model.AppUserRole;
import org.authservice.repo.AppUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AdminInitializer {

    private static final Logger loger = LoggerFactory.getLogger(AdminInitializer.class);
    @Value("${secrets.initializer.admin_password}")
    private String password;

    @Bean
    CommandLineRunner createAdmin (AppUserRepo appUserRepo, PasswordEncoder encoder) {
        return args -> {

            if (appUserRepo.findAppUserByEmail("sine@gmail.com").isEmpty()) {
                AppUser user = new AppUser();
                user.setEmail("sine@gmail.com");
                user.setFirstName("sine");
                user.setLastName("oussama");
                user.setRole(AppUserRole.SUPER_ADMIN);
                user.setPassword(encoder.encode(password));

                appUserRepo.save( user );
            } else {
                loger.info("this email is already taken");
            }

        };
    }

}
