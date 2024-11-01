package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.common.exception.FileStorageException;
import com.primetech.primetech_store.common.exception.InvalidFileFormatException;
import com.primetech.primetech_store.common.filesystem.FileStorageService;
import com.primetech.primetech_store.user.domain.interfaces.UserImageServiceInterface;
import com.primetech.primetech_store.user.domain.models.UserImage;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class UploadUserImageApplication {

    private final UserImageServiceInterface uploadUserImageService;
    private final FileStorageService fileStorageService;

    public void uploadUserProfile(MultipartFile file, String email) {
        String originalName = file.getOriginalFilename();

        if (!fileStorageService.isValidFile(file, originalName)) {
            throw new InvalidFileFormatException("Invalid file format or size");
        }

        String fileName = UUID.randomUUID().toString();
        String newFileName = fileName + originalName.substring(originalName.lastIndexOf("."));

        // Eliminar imágenes anteriores
        List<UserImage> userImages = uploadUserImageService.findUserImage(email);
        userImages.forEach(image -> {
            try {
                fileStorageService.deleteFile(image.getImgURL());
            } catch (IOException e) {
                throw new FileStorageException("Could not delete previous image");
            }
        });

        // Guardar el nuevo archivo
        try {
            String relativePath = fileStorageService.saveFile(file, "userImages" ,newFileName);
            uploadUserImageService.uploadUserProfile(email, relativePath);
        } catch (IOException e) {
            throw new FileStorageException("Error saving file");
        }
    }
}
