package com.primetech.primetech_store.user.infraestructure.services;

import com.primetech.primetech_store.common.exception.UserImageDeletionException;
import com.primetech.primetech_store.common.exception.UserImageNotFoundException;
import com.primetech.primetech_store.user.domain.interfaces.UserImageServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserImage;
import com.primetech.primetech_store.user.infraestructure.repositories.UserImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserImageService implements UserImageServiceInterface {

    private final UserImageRepository userImageRepository;

    private final UserService userService;

    @Override
    public void uploadUserProfile(String email, String url) {
        User user = userService.findUserInformationByEmail(email);
        List<UserImage> userImages = userImageRepository.findByUser_UserId(user.getUserId());

        if (!userImages.isEmpty()) {
            // Actualizar la URL de la imagen existente
            UserImage userImage = userImages.get(0);
            userImage.setImgURL(url);
            userImageRepository.save(userImage);
        } else {
            // Crear una nueva entrada
            UserImage userImage = new UserImage();
            userImage.setUser(user);
            userImage.setImgURL(url);
            userImageRepository.save(userImage);
        }
    }


    @Override
    public List<UserImage> findUserImage(String email) {
        User user = userService.findUserInformationByEmail(email);
        return userImageRepository.findByUser_UserId(user.getUserId());
    }

    @Override
    public void deleteUserImage(String email, UUID userImageId) {
        userService.findUserInformationByEmail(email);
        Optional<UserImage> userImageOptional = userImageRepository.findByUserImageId(userImageId);

        userImageRepository.findByUserImageId(userImageId);
        if (userImageOptional.isEmpty()) {
            throw new UserImageNotFoundException("User image not found");
        }
        UserImage image = userImageOptional.get();
        try {
            userImageRepository.delete(image);
        } catch (Exception e) {
            throw new UserImageDeletionException("Failed to delete user image from database");
        }
    }
}
