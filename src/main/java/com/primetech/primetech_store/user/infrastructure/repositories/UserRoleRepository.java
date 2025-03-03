package com.primetech.primetech_store.user.infrastructure.repositories;

import com.primetech.primetech_store.user.domain.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    Optional<UserRole> findByRoleName(String roleName);
    List<UserRole> findByRoleIdIn(List<UUID> roleIds);
}
