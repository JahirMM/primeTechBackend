package com.primetech.primetech_store.user.infraestructure.repositories;

import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface UserRoleAssignmentRepository extends JpaRepository<UserRoleAssignment, UUID> {
    List<UserRoleAssignment> findByUser_UserId(UUID userId);
    List<UserRoleAssignment> findByUser_UserIdAndUserRole_RoleId(UUID userId, UUID roleId);
}
