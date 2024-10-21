package com.primetech.primetech_store.auth.domain.interfaces;

import com.primetech.primetech_store.auth.application.dto.LoginRequest;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserRole;
import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;

public interface AuthServiceInterface {
    User findUserByEmail(LoginRequest request);
    User createUser(User user);
    UserRole findRolByRoleName(String roleName);
    UserRoleAssignment saveAssignedRole(UserRoleAssignment userRoleAssignment);
}
