package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.UserNotSellerException;
import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.product.domain.models.*;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class DeleteProductApplication {
    public final UserServiceInterface userService;
    public final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    public final ProductServiceInterface productService;
    public final DeviceServiceInterface deviceService;

    private final ProductRelationshipServiceInterface productRelationshipService;

    @Transactional
    public void deleteProductApplication(UUID productId, String email) {
        User user = userService.findUserInformationByEmail(email);
        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        // buscamos el product y el device relacionado
        Product product = productService.findProductByProductId(productId);
        Device device = deviceService.findDevicebyProductId(product.getProductId());

        // eliminamos todas las relacion de product
        productRelationshipService.deleteProductRelationships(device.getDeviceId(), product.getProductId());

        // eliminamos el device
        deviceService.deleteDeviceByDeviceId(device.getDeviceId());

        // eliminamos el product
        productService.deleteProductByProductId(product.getProductId());
    }
}
