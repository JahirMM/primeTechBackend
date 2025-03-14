package com.primetech.primetech_store.user.infrastructure.repositories;

import com.primetech.primetech_store.user.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserId(UUID userId);
    boolean existsByEmail(String email);
}
