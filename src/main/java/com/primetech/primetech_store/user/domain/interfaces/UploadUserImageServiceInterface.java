package com.primetech.primetech_store.user.domain.interfaces;

import com.primetech.primetech_store.user.domain.models.UserImage;

import java.util.List;

public interface UploadUserImageServiceInterface {
    void uploadUserProfile(String email, String url);
    List<UserImage> findUserImage(String email);
}
