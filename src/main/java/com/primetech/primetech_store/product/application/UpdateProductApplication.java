package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.product.application.DTO.product.ProductRequestDTO;
import com.primetech.primetech_store.product.application.DTO.product.ProductDTO;
import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.product.domain.models.*;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class UpdateProductApplication {
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;
    private final CategoryServiceInterface categoryService;
    private final DeviceTypeServiceInterface deviceTypeService;

    private final DeviceServiceInterface deviceService;
    private final LaptopServiceInterface laptopService;
    private final MobileDeviceServiceInterface mobileDeviceService;

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

        Device device = deviceService.findDevicebyProductId(product.getProductId());
        DeviceType deviceType = determineDeviceByCategory(category, device.getDeviceId());

        device.setProduct(product);
        device.setDeviceType(deviceType);

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

    private DeviceType determineDeviceByCategory(Category category, UUID deviceId) {
        switch (category.getCategoryName().toLowerCase()) {
            case "cellular":
            case "tablet":
                List<Laptop> laptops = laptopService.findLaptopInformationByDeviceId(deviceId);
                if (!laptops.isEmpty()) {
                    laptopService.deleteLaptopByLaptopId(laptops.get(0).getLaptopId());
                }
                return deviceTypeService.findDeviceTypeByTypeName("mobile");
            case "laptop":
                return deviceTypeService.findDeviceTypeByTypeName("laptop");
            default:
                List<MobileDevice> mobileDevices = mobileDeviceService.findMobileDeviceInformationByDeviceId(deviceId);
                if (!mobileDevices.isEmpty()) {
                    mobileDeviceService.deleteMobileDeviceByMobileDeviceId(mobileDevices.get(0).getMobileDeviceId());
                }
                return  deviceTypeService.findDeviceTypeByTypeName("other");
        }
    }
}
