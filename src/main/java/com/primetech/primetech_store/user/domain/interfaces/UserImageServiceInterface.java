package com.primetech.primetech_store.user.domain.interfaces;

import com.primetech.primetech_store.user.domain.models.UserImage;

import java.util.List;
import java.util.UUID;

public interface UserImageServiceInterface {
    void uploadUserProfile(String email, String url);
    List<UserImage> findUserImage(String email);
    void deleteUserImage(String email, UUID userImageId);
}
