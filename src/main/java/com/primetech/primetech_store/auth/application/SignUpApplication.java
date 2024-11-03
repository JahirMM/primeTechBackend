package com.primetech.primetech_store.auth.application;

import com.primetech.primetech_store.auth.application.dto.SignUpRequest;
import com.primetech.primetech_store.auth.domain.interfaces.AuthServiceInterface;
import com.primetech.primetech_store.common.exception.EmailAlreadyExistsException;
import com.primetech.primetech_store.common.exception.InvalidFieldFormatException;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserRole;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SignUpApplication {

    private final PasswordEncoder passwordEncoder;
    private final AuthServiceInterface authService;

    public SignUpApplication(PasswordEncoder passwordEncoder, AuthServiceInterface authService) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void assignRole(User user) {
        UserRole role = authService.findRolByRoleName("normal");
        UserRoleAssignment roleAssigment = new UserRoleAssignment();
        // alacenamos el User en roleAssigment para manejar sus datos
        roleAssigment.setUser(user);
        // Asignamos un roleName al usuario almacenadao
        roleAssigment.setUserRole(role);

        authService.saveAssignedRole(roleAssigment);
    }

    private void validateRequestFields(SignUpRequest request) {
        if (!request.getFirstName().matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidFieldFormatException("First name must contain only letters and spaces");
        }
        if (request.getMiddleName() != null && !request.getMiddleName().matches("^[a-zA-Z\\s]*$")) {
            throw new InvalidFieldFormatException("Middle name must contain only letters and spaces");
        }
        if (!request.getPaternalSurname().matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidFieldFormatException("Paternal surname must contain only letters and spaces");
        }
        if (!request.getMaternalSurname().matches("^[a-zA-Z\\s]+$")) {
            throw new InvalidFieldFormatException("Maternal surname must contain only letters and spaces");
        }
    }

    public User signUp(SignUpRequest request) {
        validateRequestFields(request);

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
}
