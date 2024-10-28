package com.primetech.primetech_store.user.infraestructure.controllers;

import com.primetech.primetech_store.user.application.GetUserInformationApplication;
import com.primetech.primetech_store.user.application.UpdateUserInformationApplication;
import com.primetech.primetech_store.user.application.dto.UpdateUserInformationRequestDTO;
import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.application.dto.UserInformationResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final GetUserInformationApplication userInformationApplication;
    private final UpdateUserInformationApplication updateUserInformationApplication;

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

    @PostMapping(value = "/user")
    public ResponseEntity<UserInformationResponseDTO> updateUserInformation(@Valid @RequestBody UpdateUserInformationRequestDTO userRequest, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                UserDTO userDTO = updateUserInformationApplication.updateUserInformation(userRequest, authentication);
                return ResponseEntity.ok(new UserInformationResponseDTO(userDTO, "Data has been correctly updated"));
            } catch (IllegalArgumentException ex) {
                System.out.println("Error de validación: " + ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new UserInformationResponseDTO(null, ex.getMessage()));
            } catch (RuntimeException ex) {
                System.out.println("Error interno del servidor: " + ex.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new UserInformationResponseDTO(null, "Error interno del servidor"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserInformationResponseDTO(null, "Please log in"));
        }
    }

}
