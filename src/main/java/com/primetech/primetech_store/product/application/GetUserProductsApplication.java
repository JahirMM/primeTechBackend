package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.product.ProductDTO;
import com.primetech.primetech_store.product.application.DTO.product.UserProductDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductImageServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.product.domain.models.ProductImage;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class GetUserProductsApplication {
    private final UserRoleAssignmentServiceInterface roleAssignmentService;
    private final ProductImageServiceInterface productImageService;
    private final ProductServiceInterface productService;
    private final DeviceServiceInterface deviceService;
    private final UserServiceInterface userService;

    @Transactional
    public List<UserProductDTO> getUserProducts(String email) {
        User user = userService.findUserInformationByEmail(email);

        if (!roleAssignmentService.isSeller(user)) {
            throw new  UserNotSellerException("User is not seller");
        }

        List<Product> products = productService.findByUserId(user.getUserId());

        return products.stream().map(product -> {
            Device device = deviceService.findDevicebyProductId(product.getProductId());
            ProductImage productImage = productImageService.findProductImagByProductIdAndMainTrue(product.getProductId());
            String imageUrl = productImage != null ? productImage.getImgURL() : "";

            return new UserProductDTO(product, imageUrl, device.getDeviceType().getTypeName());
        }).toList();
    }
}
