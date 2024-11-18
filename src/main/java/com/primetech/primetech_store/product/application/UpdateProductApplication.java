package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.product.application.DTO.product.ProductRequestDTO;
import com.primetech.primetech_store.product.application.DTO.product.ProductDTO;
import com.primetech.primetech_store.product.domain.interfaces.CategoryServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceTypeServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Category;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.domain.models.Product;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateProductApplication {
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;
    private final CategoryServiceInterface categoryService;
    private final DeviceTypeServiceInterface deviceTypeService;

    @Transactional
    public ProductDTO updateProductApplication(UUID productId, String email, ProductRequestDTO request) {
        User user = userService.findUserInformationByEmail(email);

        Product product = productService.findByProductIdAndSellerId(productId, user.getUserId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());

        Category category = categoryService.findCategoryByCategoryName(request.getCategory());
        product.setCategory(category);

        Product saveProduct = productService.saveProduct(product);
        DeviceType deviceType = determineDeviceByCategory(category);

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
