package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.category.domain.interfaces.CategoryServiceInterface;
import com.primetech.primetech_store.category.domain.models.Category;
import com.primetech.primetech_store.product.application.DTO.product.ProductRequestDTO;
import com.primetech.primetech_store.product.application.DTO.product.ProductDTO;
import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.product.domain.models.*;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
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
    private final SimCardServiceInterface simCardService;
    private final CameraServiceInterface cameraService;

    @Transactional
    public ProductDTO updateProductApplication(UUID productId, String email, ProductRequestDTO request) {
        User user = userService.findUserInformationByEmail(email);

        Product product = productService.findByProductIdAndSellerId(productId, user.getUserId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice().setScale(3, RoundingMode.HALF_UP));

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
                deleteLaptopData(deviceId);
                deleteCameraData(deviceId);
                return deviceTypeService.findDeviceTypeByTypeName("mobile");

            case "laptop":
                deleteMobileDeviceData(deviceId);
                return deviceTypeService.findDeviceTypeByTypeName("laptop");

            default:
                deleteLaptopData(deviceId);
                deleteMobileDeviceData(deviceId);
                return deviceTypeService.findDeviceTypeByTypeName("other");
        }
    }

    private void deleteLaptopData(UUID deviceId) {
        List<Laptop> laptops = laptopService.findLaptopInformationByDeviceId(deviceId);
        if (!laptops.isEmpty()) {
            laptopService.deleteLaptopByLaptopId(laptops.get(0).getLaptopId());
        }
    }

    private void deleteMobileDeviceData(UUID deviceId) {
        List<MobileDevice> mobileDevices = mobileDeviceService.findMobileDeviceInformationByDeviceId(deviceId);
        if (!mobileDevices.isEmpty()) {
            UUID mobileDeviceId = mobileDevices.get(0).getMobileDeviceId();
            deleteSimCardData(mobileDeviceId);
            deleteCameraData(deviceId);
            mobileDeviceService.deleteMobileDeviceByMobileDeviceId(mobileDeviceId);
        }
    }

    private void deleteSimCardData(UUID mobileDeviceId) {
        List<SimCard> simCards = simCardService.findSimCardInformationByMobileDevice(mobileDeviceId);
        if (!simCards.isEmpty()) {
            simCardService.deleteSimCardBySimCardId(simCards.get(0).getSimCardId());
        }
    }

    private void deleteCameraData(UUID deviceId) {
        List<Camera> cameras = cameraService.findCameraInformationByDeviceId(deviceId);
        if (!cameras.isEmpty()) {
            cameras.forEach(camera -> cameraService.deleteCameraByCameraId(camera.getCameraId()));
        }
    }
}
