package com.primetech.primetech_store.user.infraestructure.controllers;

import com.primetech.primetech_store.common.exception.RoleNotFoundException;
import com.primetech.primetech_store.common.exception.UserAlreadyHasRoleException;
import com.primetech.primetech_store.common.exception.UserNotFoundException;
import com.primetech.primetech_store.user.application.AssignRoleApplication;
import com.primetech.primetech_store.user.application.GetUserInformationApplication;
import com.primetech.primetech_store.user.application.UpdateUserInformationApplication;
import com.primetech.primetech_store.user.application.dto.AssignRoleResponseDTO;
import com.primetech.primetech_store.user.application.dto.UpdateUserInformationRequestDTO;
import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.application.dto.UserInformationResponseDTO;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.infraestructure.services.UserService;
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
    private final AssignRoleApplication assignRoleApplication;
    private final UserService userService;

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
            } catch (UserNotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new UserInformationResponseDTO(null, ex.getMessage()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserInformationResponseDTO(null, "Please log in"));
        }
    }

    @PostMapping(value = "/user")
    public ResponseEntity<UserInformationResponseDTO> updateUserInformation(@Valid @RequestBody UpdateUserInformationRequestDTO userRequest) {
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
                        .body(new UserInformationResponseDTO(null, "Internal server error"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserInformationResponseDTO(null, "Please log in"));
        }
    }

    @PostMapping("/assign-role/{rolName}")
    public ResponseEntity<AssignRoleResponseDTO> assignSellerRole(@PathVariable String rolName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                User user = userService.findUserInformationByEmail(authentication.getName());
                UserDTO userDTO = assignRoleApplication.assignRole(user.getUserId(), rolName);
                return ResponseEntity.ok(new AssignRoleResponseDTO(userDTO, "correctly assigned role"));
            } catch (UserAlreadyHasRoleException ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new AssignRoleResponseDTO(null, ex.getMessage()));
            } catch (UserNotFoundException | RoleNotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new AssignRoleResponseDTO(null, ex.getMessage()));
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new AssignRoleResponseDTO(null, ex.getMessage()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AssignRoleResponseDTO(null, "Please log in"));
        }
    }
}
