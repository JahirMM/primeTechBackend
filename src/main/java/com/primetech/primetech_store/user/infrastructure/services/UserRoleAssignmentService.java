package com.primetech.primetech_store.user.infrastructure.services;

import com.primetech.primetech_store.common.application.exception.RoleNotFoundException;
import com.primetech.primetech_store.common.application.exception.UserAlreadyHasRoleException;
import com.primetech.primetech_store.common.application.exception.UserRoleAssignmentNotFoundException;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserRole;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import com.primetech.primetech_store.user.infrastructure.repositories.UserRoleAssignmentRepository;
import com.primetech.primetech_store.user.infrastructure.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleAssignmentService implements UserRoleAssignmentServiceInterface {

    private final UserRoleAssignmentRepository userRoleAssignmentRepository;
    private final UserRoleRepository userRoleRepository;

    private final UserService userService;

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
    public UserRoleAssignment saveUserRoleAssignment(String email, String roleName) {
        User user = userService.findUserInformationByEmail(email);
        UserRole userRole = userRoleRepository.findByRoleName(roleName).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        List<UserRoleAssignment> assignedRoles = userRoleAssignmentRepository.findByUser_UserIdAndUserRole_RoleId(user.getUserId(), userRole.getRoleId());

        if (!assignedRoles.isEmpty()) {
            throw new UserAlreadyHasRoleException("The user already has that role assigned");
        }

        UserRoleAssignment userRoleAssignment = new UserRoleAssignment();
        userRoleAssignment.setUser(user);
        userRoleAssignment.setUserRole(userRole);

        return userRoleAssignmentRepository.save(userRoleAssignment);
    }

    @Override
    public void deleteAssignedRole(String email, String roleName) {
        User user = userService.findUserInformationByEmail(email);
        UserRole userRole = userRoleRepository.findByRoleName(roleName).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        List<UserRoleAssignment> userRoleAssignments = userRoleAssignmentRepository.findByUser_UserIdAndUserRole_RoleId(user.getUserId(), userRole.getRoleId());

        if (userRoleAssignments.isEmpty()) {
            throw new UserRoleAssignmentNotFoundException("No role assignment found for the specified user and role");
        }

        userRoleAssignmentRepository.deleteAll(userRoleAssignments);
    }

    @Override
    public boolean isSeller(User user) {
        UserRole userRole = userRoleRepository.findByRoleName("seller").orElseThrow(() -> new RoleNotFoundException("Role not found"));
        List<UserRoleAssignment> userRoleAssignments = userRoleAssignmentRepository.findByUser_UserIdAndUserRole_RoleId(user.getUserId(), userRole.getRoleId());
        for (UserRoleAssignment roleAssignment : userRoleAssignments) {
            if (roleAssignment.getUserRole().getRoleId().equals(userRole.getRoleId())) {
                return true;
            }
        }
        return false;
    }

}
