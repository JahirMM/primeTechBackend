package com.primetech.primetech_store.sellerInformation.application;

import com.primetech.primetech_store.common.application.exception.UserNotSellerException;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.sellerInformation.application.DTO.SellerInformationDTO;
import com.primetech.primetech_store.user.domain.interfaces.UserImageServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import com.primetech.primetech_store.user.domain.models.UserImage;
import com.primetech.primetech_store.user.infraestructure.services.UserRoleAssignmentService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class GetSellerInformationApplication {
    private final UserRoleAssignmentService userRoleAssignment;
    private final UserImageServiceInterface userImageService;
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;

    public SellerInformationDTO getSellerInformation(UUID userId) {
        User user = userService.findUserInformationByUserId(userId);

        if (!userRoleAssignment.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller");
        }

        long quantityProducts = productService.countProductByUserId(user.getUserId());
        List<UserImage> userImages = userImageService.findUserImage(user.getEmail());
        String imageUrl = userImages.size() > 0 ? userImages.get(0).getImgURL() : null;
        return new SellerInformationDTO(user.getFirstName(), user.getPaternalSurname(), imageUrl, quantityProducts);
    }
}
