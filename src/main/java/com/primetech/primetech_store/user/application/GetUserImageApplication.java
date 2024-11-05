package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.domain.interfaces.UserImageServiceInterface;
import com.primetech.primetech_store.user.domain.models.UserImage;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetUserImageApplication {
    private final UserImageServiceInterface userImageService;

    public UserImage getUserImage(String email) {
        List<UserImage> userImages =  userImageService.findUserImage(email);
        return userImages.get(0);
    }
}
