package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.common.exception.FileStorageException;
import com.primetech.primetech_store.common.filesystem.FileStorageService;
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
        // elimnar la imagen del sistema de archivos
        for (UserImage userImage:userImages) {
            imageUrl = userImage.getImgURL();
        }

        // eliminar la imgen en la base de datos
        userImageService.deleteUserImage(email, userImageId);

        // elimnar la imagen del sistema de archivos
        try {
            fileStorageService.deleteFile(imageUrl);
        } catch (IOException e) {
            throw new FileStorageException("Could not delete previous image");
        }
    }
}
