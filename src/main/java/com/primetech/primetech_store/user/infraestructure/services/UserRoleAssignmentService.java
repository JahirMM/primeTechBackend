package com.primetech.primetech_store.user.infraestructure.services;

import com.primetech.primetech_store.common.exception.RoleNotFoundException;
import com.primetech.primetech_store.common.exception.UserAlreadyHasRoleException;
import com.primetech.primetech_store.common.exception.UserNotFoundException;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
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
public class UserRoleAssignmentService implements UserRoleAssignmentServiceInterface {

    private final UserRoleAssignmentRepository userRoleAssignmentRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

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

    @Override
    public UserRoleAssignment saveUserRoleAssignment(UUID userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        UserRole userRole = userRoleRepository.findByRoleName(roleName).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        List<UserRoleAssignment> assignedRoles = userRoleAssignmentRepository.findByUser_UserIdAndUserRole_RoleId(user.getUserId(), userRole.getRoleId());

        if (!assignedRoles.isEmpty()) {
            throw new UserAlreadyHasRoleException("El usuario ya tiene el rol de vendedor");
        }

        UserRoleAssignment userRoleAssignment = new UserRoleAssignment();
        userRoleAssignment.setUser(user);
        userRoleAssignment.setUserRole(userRole);

        return userRoleAssignmentRepository.save(userRoleAssignment);
    }
}