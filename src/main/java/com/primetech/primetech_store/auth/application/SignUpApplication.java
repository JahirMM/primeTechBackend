package com.primetech.primetech_store.auth.application;

import com.primetech.primetech_store.auth.application.dto.SignUpRequestDTO;
import com.primetech.primetech_store.auth.domain.interfaces.AuthServiceInterface;
import com.primetech.primetech_store.common.application.exception.EmailAlreadyExistsException;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserRole;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class SignUpApplication {

    private final PasswordEncoder passwordEncoder;
    private final AuthServiceInterface authService;

    public User signUp(SignUpRequestDTO request) {
        String email = request.getEmail();
        if (authService.emailExists(email)) {
            throw new EmailAlreadyExistsException("The email address is already registered.");
        }
        String password = hashPassword(request.getPassword());
        User user = new User(email, password, request.getFirstName(), request.getMiddleName(), request.getPaternalSurname(), request.getMaternalSurname());
        User savedUser = authService.createUser(user);

        authService.createUser(savedUser);
        assignRole(savedUser);
        return savedUser;
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void assignRole(User user) {
        UserRole role = authService.findRoleByRoleName("normal");
        UserRoleAssignment roleAssigment = new UserRoleAssignment();
        // alacenamos el User en roleAssigment para manejar sus datos
        roleAssigment.setUser(user);
        // Asignamos un roleName al usuario almacenadao
        roleAssigment.setUserRole(role);

        authService.saveAssignedRole(roleAssigment);
    }
}
