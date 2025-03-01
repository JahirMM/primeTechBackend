package com.primetech.primetech_store.auth.application;

import com.primetech.primetech_store.auth.domain.interfaces.AuthServiceInterface;
import com.primetech.primetech_store.common.application.exception.EmailChangeException;
import com.primetech.primetech_store.jwt.services.JwtService;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class ChangeEmailApplicaction {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceInterface userService;
    private final AuthServiceInterface authService;
    private final JwtService jwtService;

    @Transactional
    public void changeEmail(String currentEmail, String password, String newEmail, HttpServletResponse response) {
        User user = userService.findUserInformationByEmail(currentEmail);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new EmailChangeException("Contraseña incorrecta");
        }

        if (currentEmail.equals(newEmail)) {
            throw new EmailChangeException("El nuevo correo electrónico debe ser diferente del actual.");
        }

        if (authService.emailExists(newEmail)) {
            throw new EmailChangeException("El correo electrónico ya se utiliza.");
        }

        user.setEmail(newEmail);
        userService.saveUser(user);
        updateAuthentication(newEmail, password, response);
    }

    private void updateAuthentication(String newEmail, String password, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(newEmail, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.getToken((UserDetails) authentication.getPrincipal());

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        String cookieHeader = String.format(
                "jwt=%s; HttpOnly; Path=/; Secure; Max-Age=%d; SameSite=Strict",
                token,
                24 * 60 * 60
        );
        response.setHeader("Set-Cookie", cookieHeader);
    }
}
