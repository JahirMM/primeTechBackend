package com.primetech.primetech_store.auth.infraestructure.controllers;

import com.primetech.primetech_store.auth.application.LoginApplication;
import com.primetech.primetech_store.auth.application.SignUpApplication;
import com.primetech.primetech_store.auth.domain.dto.*;
import com.primetech.primetech_store.jwt.services.JwtService;
import com.primetech.primetech_store.user.domain.models.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    private final LoginApplication loginApplication;
    private final SignUpApplication signUpApplication;

    @PostMapping(value = "login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {

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

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("session successfully logged in");
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(value = "signUp")
    public ResponseEntity<SignUpResponse> register(@Valid @RequestBody SignUpRequest request) {
        User user = signUpApplication.signUp(request);

        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("user successfully created");
        signUpResponse.setUser(user);
        return ResponseEntity.ok(signUpResponse);
    }
}
