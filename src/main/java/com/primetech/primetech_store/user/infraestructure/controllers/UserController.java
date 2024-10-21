package com.primetech.primetech_store.user.infraestructure.controllers;

import com.primetech.primetech_store.user.application.GetUserInformationApplication;
import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.application.dto.UserInformationResponseDTO;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final GetUserInformationApplication userInformationApplication;

    @GetMapping(value = "user")
    public ResponseEntity<UserInformationResponseDTO> getUserInformation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            try {
                UserDTO userDTO = userInformationApplication.getUserInformation(authentication.getName());
                if (userDTO == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new UserInformationResponseDTO(null, "User not found"));
                }
                return ResponseEntity.ok(new UserInformationResponseDTO(userDTO, "User found"));
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new UserInformationResponseDTO(null, "User not found"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserInformationResponseDTO(null, "Please log in"));
        }
    }
}
