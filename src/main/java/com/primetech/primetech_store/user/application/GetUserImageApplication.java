package com.primetech.primetech_store.user.application;

import com.primetech.primetech_store.user.application.dto.UserImageDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserImageServiceInterface;
import com.primetech.primetech_store.user.domain.models.UserImage;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class GetUserImageApplication {
    private final UserImageServiceInterface userImageService;

    @Transactional
    public UserImageDTO getUserImage(String email) {
        List<UserImage> userImages = userImageService.findUserImage(email);

        if (userImages.isEmpty()) {
            return null;
        }

        UserImage userImage = userImages.get(0);
        return new UserImageDTO(userImage.getUserImageId(), userImage.getImgURL());
    }
}
