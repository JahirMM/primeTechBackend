package com.primetech.primetech_store.auth.infraestructure.services;

import com.primetech.primetech_store.auth.application.dto.LoginRequest;
import com.primetech.primetech_store.auth.domain.interfaces.AuthServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserRole;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import com.primetech.primetech_store.user.infraestructure.repositories.UserRepository;
import com.primetech.primetech_store.user.infraestructure.repositories.UserRoleAssignmentRepository;
import com.primetech.primetech_store.user.infraestructure.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserRoleAssignmentRepository userRoleAssignmentRepository;

    @Override
    public User findUserByEmail(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserRole findRolByRoleName(String roleName) {
        return userRoleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public UserRoleAssignment saveAssignedRole(UserRoleAssignment userRoleAssignment) {
        return userRoleAssignmentRepository.save(userRoleAssignment);
    }
}
