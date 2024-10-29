package com.primetech.primetech_store.user.domain.interfaces;

import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;

import java.util.List;
import java.util.UUID;

public interface UserServiceInterface {
    User findUserInformationByEmail(String email);
    List<UUID> findAssignedRolesByUserId(UUID userId);
    List<String> findRoleNamesByRoleIds(List<UUID> roleIds);
    User saveUser(User user);
    UserRoleAssignment saveUserRoleAssignment(UUID userId, String roleName);
}
