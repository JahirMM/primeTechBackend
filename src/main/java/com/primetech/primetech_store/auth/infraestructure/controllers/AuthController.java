package com.primetech.primetech_store.auth.infraestructure.controllers;

import com.primetech.primetech_store.auth.application.LoginApplication;
import com.primetech.primetech_store.auth.application.SignUpApplication;
import com.primetech.primetech_store.auth.domain.dto.AuthResponse;
import com.primetech.primetech_store.auth.domain.dto.LoginRequest;
import com.primetech.primetech_store.auth.domain.dto.SignUpRequest;
import com.primetech.primetech_store.auth.domain.dto.SignUpResponse;
import com.primetech.primetech_store.auth.infraestructure.services.AuthService;
import com.primetech.primetech_store.jwt.services.JwtService;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.infraestructure.CustomUserDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    private final LoginApplication loginApplication;
    private final SignUpApplication signUpApplication;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {

        // Autenticar al usuario y obtener UserDetails
        UserDetails userDetails = loginApplication.login(request);

        // Generar el token
        String token = jwtService.getToken(userDetails);

        // Crear la cookie con el token JWT
        String cookieName = "jwt";
        Cookie cookie = new Cookie(cookieName, token);

        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping(value = "signUp")
    public ResponseEntity<SignUpResponse> register(@Valid @RequestBody SignUpRequest request) {
        User user = signUpApplication.signUp(request);

        CustomUserDetails userDetails = new CustomUserDetails(user);
        AuthResponse token = AuthResponse.builder()
                .token(jwtService.getToken(userDetails))
                .build();

        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("user successfully created");
        signUpResponse.setUser(user);
        signUpResponse.setToken(token.getToken());
        return ResponseEntity.ok(signUpResponse);
    }
}
