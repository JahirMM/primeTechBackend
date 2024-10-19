package com.primetech.primetech_store.user.infraestructure.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    @PostMapping(value = "demo")
    public String welcome() {
        // Obtener el usuario autenticado directamente desde el contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return "Bienvenido a la tienda";
        } else {
            return "Acceso no autorizado";
        }
    }
}
