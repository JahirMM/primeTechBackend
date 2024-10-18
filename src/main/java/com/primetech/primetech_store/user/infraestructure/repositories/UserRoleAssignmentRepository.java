package com.primetech.primetech_store.user.infraestructure.repositories;

import com.primetech.primetech_store.user.domain.models.UserRoleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleAssignmentRepository extends JpaRepository<UserRoleAssignment, UUID> {
}
