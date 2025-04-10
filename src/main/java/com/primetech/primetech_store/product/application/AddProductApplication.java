package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.application.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.product.ProductRequestDTO;
import com.primetech.primetech_store.product.application.DTO.product.ProductDTO;
import com.primetech.primetech_store.category.domain.interfaces.CategoryServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceTypeServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.category.domain.models.Category;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;

@AllArgsConstructor
public class AddProductApplication {
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;
    private final CategoryServiceInterface categoryService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final DeviceServiceInterface deviceService;
    private final DeviceTypeServiceInterface deviceTypeService;

    @Transactional
    public ProductDTO addProduct(ProductRequestDTO request, String email) {
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        Product product = new Product(request.getName(), request.getDescription(), request.getBrand(), request.getStock(), request.getPrice().setScale(3, RoundingMode.HALF_UP));
        product.setUser(user);

        // buscar categoria por category_name para asignar al producto
        Category category = categoryService.findCategoryByCategoryName(request.getCategory());
        product.setCategory(category);

        // guardar el producto en la tabla product
        Product saveProduct = productService.saveProduct(product);

        // buscar el deviceType que se tiene que agregar
        DeviceType deviceType = determineDeviceByCategory(category);

        // guardamos la relacion de producto con DeviceType
        Device device = new Device();
        device.setProduct(product);
        device.setDeviceType(deviceType);

        deviceService.saveDevice(product, deviceType);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(saveProduct.getProductId());
        productDTO.setName(saveProduct .getName());
        productDTO.setDescription(saveProduct .getDescription());
        productDTO.setBrand(saveProduct .getBrand());
        productDTO.setStock(saveProduct .getStock());
        productDTO.setPrice(saveProduct .getPrice());
        productDTO.setCategory(category.getCategoryName());
        productDTO.setDeviceType(deviceType.getTypeName());
        productDTO.setCreatedAt(saveProduct .getCreatedAt());
        productDTO.setUpdatedAt(saveProduct.getUpdatedAt());

        return productDTO;
    }

    private DeviceType determineDeviceByCategory(Category category) {
        switch (category.getCategoryName().toLowerCase()) {
            case "cellular":
            case "tablet":
                return deviceTypeService.findDeviceTypeByTypeName("mobile");

            case "laptop":
                return deviceTypeService.findDeviceTypeByTypeName("laptop");
            default:
                return  deviceTypeService.findDeviceTypeByTypeName("other");
        }
    }
}
