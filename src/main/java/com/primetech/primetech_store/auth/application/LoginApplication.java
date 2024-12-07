package com.primetech.primetech_store.auth.application;

import com.primetech.primetech_store.auth.application.dto.LoginRequestDTO;
import com.primetech.primetech_store.auth.domain.interfaces.AuthServiceInterface;
import com.primetech.primetech_store.common.application.exception.InvalidCredentialsException;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.infraestructure.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class LoginApplication {
    private final AuthenticationManager authenticationManager;
    private final AuthServiceInterface authService;

    public UserDetails login(LoginRequestDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = authService.findUserByEmail(request);

            return new CustomUserDetails(user);
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }
}
