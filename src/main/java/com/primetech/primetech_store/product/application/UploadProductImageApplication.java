package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.FileStorageException;
import com.primetech.primetech_store.common.exception.InvalidFileFormatException;
import com.primetech.primetech_store.common.filesystem.FileStorageService;
import com.primetech.primetech_store.product.application.DTO.productImage.UploadImageRequestDTO;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
public class UploadProductImageApplication {
    private final ProductImageServiceInterface productImageService;
    private final FileStorageService fileStorageService;

    @Transactional
    public void uploadProductImage(MultipartFile file, String email, UUID productId, UploadImageRequestDTO request){
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
