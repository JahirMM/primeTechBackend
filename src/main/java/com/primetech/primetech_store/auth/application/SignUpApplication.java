package com.primetech.primetech_store.auth.application;

import com.primetech.primetech_store.auth.application.dto.SignUpRequest;
import com.primetech.primetech_store.auth.domain.interfaces.AuthServiceInterface;
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

    private String hasPasswrod(String password) {
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

    public User signUp(SignUpRequest request) {
        String email = request.getEmail();
        String password = hasPasswrod(request.getPassword());
        String firstName = request.getFirstName();
        String middleName = request.getMiddleName();
        String paternalSurname = request.getPaternalSurname();
        String maternalSurname = request.getMaternalSurname();

        User user = new User(email, password, firstName, middleName, paternalSurname, maternalSurname);
        User savedUser = authService.createUser(user);

        authService.createUser(savedUser);
        assignRole(savedUser);
        return savedUser;
    }
}
