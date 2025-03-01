package com.primetech.primetech_store.config.authConfig;

import com.primetech.primetech_store.auth.application.ChangeEmailApplicaction;
import com.primetech.primetech_store.auth.application.LoginApplication;
import com.primetech.primetech_store.auth.application.SignUpApplication;
import com.primetech.primetech_store.auth.domain.interfaces.AuthServiceInterface;
import com.primetech.primetech_store.jwt.services.JwtService;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class AuthApplicationConfig {
    private final AuthenticationManager authenticationManager;
    private final AuthServiceInterface authService;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceInterface userService;
    private final JwtService jwtService;


    @Bean
    public LoginApplication loginApplication() {
        return new LoginApplication(authenticationManager, authService);
    }

    @Bean
    public SignUpApplication signUpApplication() {
        return new SignUpApplication(passwordEncoder, authService);
    }

    @Bean
    public ChangeEmailApplicaction changeEmailApplicaction() {return  new ChangeEmailApplicaction(authenticationManager, passwordEncoder, userService, authService, jwtService);}
}
