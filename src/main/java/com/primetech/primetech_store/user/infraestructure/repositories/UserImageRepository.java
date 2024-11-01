package com.primetech.primetech_store.user.infraestructure.repositories;

import com.primetech.primetech_store.user.domain.models.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserImageRepository extends JpaRepository<UserImage, UUID> {
    List<UserImage> findByUser_UserId(UUID userId);
}
