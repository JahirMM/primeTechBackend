package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.*;
import com.primetech.primetech_store.common.infrastructure.filesystem.FileStorageService;
import com.primetech.primetech_store.product.application.DTO.productImage.UploadImageRequestDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
public class UploadProductImageApplication {
    private final ProductImageServiceInterface productImageService;
    private final FileStorageService fileStorageService;
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public void uploadProductImage(MultipartFile file, String email, UUID productId, UploadImageRequestDTO request){
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        if (productImageService.countProductImageByProductId(productId) >= 5) {
            throw new MaxProductImagesException("The product already has 5 images.");
        }

        if (request.isMain() && productImageService.existsProductByProductIdAndMainTrue(productId)) {
            throw new MainProductImageAlreadyExistsException("A main product image already exists for this product");
        }

        String originalName = file.getOriginalFilename();

        if (!fileStorageService.isValidFile(file, originalName)) {
            throw new InvalidFileFormatException("Invalid file format or size");
        }

        String fileName = UUID.randomUUID().toString();
        String newFileName = fileName + originalName.substring(originalName.lastIndexOf("."));

        try {
            String relativePath = fileStorageService.saveFile(file, "productImage" ,newFileName);
            productImageService.uploadProductImage(productId, relativePath, request.isMain());
        } catch (IOException e) {
            throw new FileStorageException("Error saving file");
        }
    }
}
