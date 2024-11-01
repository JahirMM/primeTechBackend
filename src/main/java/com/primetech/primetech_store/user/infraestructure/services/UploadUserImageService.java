package com.primetech.primetech_store.user.infraestructure.services;

import com.primetech.primetech_store.user.domain.interfaces.UploadUserImageServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserImage;
import com.primetech.primetech_store.user.infraestructure.repositories.UserImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadUserImageService implements UploadUserImageServiceInterface {

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
}
