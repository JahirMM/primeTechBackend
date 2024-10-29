package com.primetech.primetech_store.user.domain.interfaces;

import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;

import java.util.List;
import java.util.UUID;

public interface UserRoleAssignmentServiceInterface {
    List<UUID> findAssignedRolesByUserId(UUID userId);
    List<String> findRoleNamesByRoleIds(List<UUID> roleIds);
    UserRoleAssignment saveUserRoleAssignment(String email, String roleName);
    void deleteAssignedRole(String email, String roleName);
}
