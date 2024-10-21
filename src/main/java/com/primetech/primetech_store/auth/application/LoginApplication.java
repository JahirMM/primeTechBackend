package com.primetech.primetech_store.auth.application;

import com.primetech.primetech_store.auth.application.dto.LoginRequest;
import com.primetech.primetech_store.auth.domain.interfaces.AuthServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.infraestructure.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginApplication {
    private final AuthenticationManager authenticationManager;
    private final AuthServiceInterface authService;

    public LoginApplication(AuthenticationManager authenticationManager, AuthServiceInterface authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    public UserDetails login(LoginRequest request) {
        // autenticar al usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // buscar el usuario
        User user = authService.findUserByEmail(request);

        return new CustomUserDetails(user);
    }
}
