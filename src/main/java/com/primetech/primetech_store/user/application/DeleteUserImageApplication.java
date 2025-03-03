package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.common.application.exception.FileStorageException;
import com.primetech.primetech_store.common.infrastructure.filesystem.FileStorageService;
import com.primetech.primetech_store.user.domain.interfaces.UserImageServiceInterface;
import com.primetech.primetech_store.user.domain.models.UserImage;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class DeleteUserImageApplication {
    private final UserImageServiceInterface userImageService;
    private final FileStorageService fileStorageService;

    @Transactional
    public void deleteUserImage(String email, UUID userImageId) {
        List<UserImage> userImages = userImageService.findUserImage(email);
        String imageUrl = "";

        for (UserImage userImage:userImages) {
            imageUrl = userImage.getImgURL();
        }

        userImageService.deleteUserImage(email, userImageId);

        try {
            fileStorageService.deleteFile(imageUrl);
        } catch (IOException e) {
            throw new FileStorageException("Could not delete previous image");
        }
    }
}
