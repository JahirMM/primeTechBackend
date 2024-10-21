package com.primetech.primetech_store.user.infraestructure.services;

import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserRole;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import com.primetech.primetech_store.user.infraestructure.repositories.UserRepository;
import com.primetech.primetech_store.user.infraestructure.repositories.UserRoleAssignmentRepository;
import com.primetech.primetech_store.user.infraestructure.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final UserRoleAssignmentRepository userRoleAssignmentRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public User findUserInformationByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UUID> findAssignedRolesByUserId(UUID userId) {
        List<UserRoleAssignment> assignments = userRoleAssignmentRepository.findByUser_UserId(userId);
        return assignments.stream()
                .map(assignment -> assignment.getUserRole().getRoleId())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findRoleNamesByRoleIds(List<UUID> roleIds) {
        return userRoleRepository.findByRoleIdIn(roleIds)
                .stream()
                .map(UserRole::getRoleName)
                .collect(Collectors.toList());
    }
}
