package com.primetech.primetech_store.user.infrastructure.repositories;

import com.primetech.primetech_store.user.domain.models.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserImageRepository extends JpaRepository<UserImage, UUID> {
    List<UserImage> findByUser_UserId(UUID userId);
    Optional<UserImage> findByUserImageId(UUID userImageId);
}
