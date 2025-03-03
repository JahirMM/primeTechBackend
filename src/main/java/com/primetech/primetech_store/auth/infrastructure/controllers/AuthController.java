package com.primetech.primetech_store.auth.infrastructure.controllers;

import com.primetech.primetech_store.auth.application.LoginApplication;
import com.primetech.primetech_store.auth.application.SignUpApplication;
import com.primetech.primetech_store.auth.application.dto.*;
import com.primetech.primetech_store.jwt.services.JwtService;
import com.primetech.primetech_store.user.domain.models.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/prime-tech/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final LoginApplication loginApplication;
    private final SignUpApplication signUpApplication;

    @PostMapping(value = "login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request, HttpServletResponse response) {
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
        String cookieHeader = String.format("%s=%s; HttpOnly; Path=/; Secure; Max-Age=%d; SameSite=Strict",
                cookieName,
                token,
                24 * 60 * 60);

        response.setHeader("Set-Cookie", cookieHeader);

        LoginResponseDTO loginResponse = new LoginResponseDTO("session successfully logged in");
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(value = "signUp")
    public ResponseEntity<SignUpResponseDTO> register(@Valid @RequestBody SignUpRequestDTO request) {
        User user = signUpApplication.signUp(request);
        UserAuthDTO userAuthDTO = new UserAuthDTO(user.getEmail(), user.getFirstName(), user.getMiddleName(), user.getPaternalSurname(), user.getMaternalSurname(), user.getCreatedAt());

        SignUpResponseDTO signUpResponse = new SignUpResponseDTO("user successfully created", userAuthDTO);
        return ResponseEntity.ok(signUpResponse);
    }

    @PostMapping(value = "logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        String cookieName = "jwt";
        Cookie cookie = new Cookie(cookieName, "");

        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.setHeader("Set-Cookie", "jwt=; HttpOnly; Secure; SameSite=Strict; Path=/; Max-Age=0");

        return ResponseEntity.noContent().build();
    }
}

