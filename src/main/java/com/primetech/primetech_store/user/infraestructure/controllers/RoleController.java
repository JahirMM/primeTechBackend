package com.primetech.primetech_store.user.infraestructure.controllers;


import com.primetech.primetech_store.common.exception.RoleNotFoundException;
import com.primetech.primetech_store.common.exception.UserAlreadyHasRoleException;
import com.primetech.primetech_store.common.exception.UserNotFoundException;
import com.primetech.primetech_store.user.application.AssignRoleApplication;
import com.primetech.primetech_store.user.application.dto.AssignRoleResponseDTO;
import com.primetech.primetech_store.user.application.dto.UserDTO;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.infraestructure.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final AssignRoleApplication assignRoleApplication;
    private final UserService userService;

    @PostMapping("/assign/{rolName}")
    public ResponseEntity<AssignRoleResponseDTO> assignRole(@PathVariable String rolName) {
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
