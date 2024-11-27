package com.primetech.primetech_store.config.productConfig;

import com.primetech.primetech_store.common.filesystem.FileStorageService;
import com.primetech.primetech_store.product.application.*;
import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.product.infraestructure.services.ProductImageService;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ProductConfig {
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;
    private final CategoryServiceInterface categoryService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final DeviceServiceInterface deviceService;
    private final DeviceTypeServiceInterface deviceTypeService;
    private final MobileDeviceServiceInterface mobileDeviceService;
    private final CameraServiceInterface cameraService;
    private final ScreenServiceInterface screenService;
    private final BatteryServiceInterface batteryService;
    private final LaptopServiceInterface laptopService;
    private final SimCardServiceInterface simCardService;

    private final ProductRelationshipServiceInterface productRelationshipService;
    private final ProductImageService productImageService;
    private final FileStorageService fileStorageService;

    /*
    * Product
    */
    @Bean
    public AddProductApplication addProductApplication(){
        return new AddProductApplication(
                productService, userService,
                categoryService, userRoleAssignmentService,
                deviceService, deviceTypeService
        );}

    @Bean
    public GetProductApplication getProductApplication() {
        return new GetProductApplication(
                productService, deviceService
        );
    }

    @Bean
    public  GetProductsApplication getProductsApplication() {
        return new GetProductsApplication(
                productService
        );
    }

    @Bean
    public UpdateProductApplication updateProductApplication() {
        return new UpdateProductApplication(
                productService, userService,
                categoryService, deviceTypeService,
                deviceService, laptopService,
                mobileDeviceService
        );
    }

    @Bean
    public DeleteProductApplication deleteProductApplication() {
        return new DeleteProductApplication(
                userService, userRoleAssignmentService,
                productService, deviceService,
                productRelationshipService
        );
    }

    /*
     * Price
     */
    @Bean
    public GetMinimumAndMaximumPrice getMinimumAndMaximumPrice() {
        return new GetMinimumAndMaximumPrice(productService);
    }

    /*
     * MobileDevice
     */
    @Bean
    public AddMobileDeviceApplication addMobileDeviceApplication(){
        return new AddMobileDeviceApplication(
                deviceTypeService, deviceService,
                productService, userService, userRoleAssignmentService,
                mobileDeviceService
        );
    }

    @Bean
    public GetMobileDeviceApplication getMobileDeviceApplication() {
        return new GetMobileDeviceApplication(
                mobileDeviceService, deviceService,
                productService
        );
    }

    @Bean
    public UpdateMobileDeviceApplication updateMobileDeviceApplication() {
        return new UpdateMobileDeviceApplication(
                mobileDeviceService, userService,
                userRoleAssignmentService
        );
    }

    /*
     * Camera
     */
    @Bean
    public AddCameraApplication addCameraApplication(){
        return new AddCameraApplication(
                userService, productService,
                userRoleAssignmentService, deviceTypeService,
                deviceService, cameraService
        );
    }

    @Bean
    public GetCameraApplication getCameraApplication() {
        return new GetCameraApplication(
                cameraService, deviceService,
                productService
        );
    }

    @Bean
    public UpdateCameraApplication updateCameraApplication() {
        return new UpdateCameraApplication(
                cameraService, userService,
                userRoleAssignmentService
        );
    }

    @Bean
    public DeleteCameraApplication deleteCameraApplication() {
        return new DeleteCameraApplication(
                cameraService, userService,
                userRoleAssignmentService
        );
    }

    /*
     * Screen
     */
    @Bean
    public AddScreenApplication addScreenApplication() {
        return new AddScreenApplication(
                userService, productService,
                userRoleAssignmentService, deviceTypeService,
                deviceService, screenService
        );
    }

    @Bean
    public GetScreenApplication getScreenApplication() {
        return new GetScreenApplication(
                screenService, deviceService,
                productService
        );
    }

    @Bean
    public UpdateScreenApplication updateScreenApplication() {
        return new UpdateScreenApplication(
                screenService, userService,
                userRoleAssignmentService
        );
    }

    @Bean
    public DeleteScreenApplication deleteScreenApplication() {
        return new DeleteScreenApplication(
                screenService, userService,
                userRoleAssignmentService
        );
    }

    /*
     * Battery
     */
    @Bean
    public AddBatteryApplication addBatteryApplication() {
        return new AddBatteryApplication(
                userService, productService,
                userRoleAssignmentService, deviceTypeService,
                deviceService, batteryService
        );
    }

    @Bean
    public GetBatteryApplication getBatteryApplication() {
        return new GetBatteryApplication(
                batteryService, deviceService,
                productService
        );
    }

    @Bean UpdateBatteryApplication updateBatteryApplication() {
        return new UpdateBatteryApplication(
                batteryService, userService,
                userRoleAssignmentService
        );
    }

    @Bean DeleteBatteryApplication deleteBatteryApplication() {
        return new DeleteBatteryApplication(
                batteryService, userService,
                userRoleAssignmentService
        );
    }

    /*
     * Laptop
     */
    @Bean
    public AddLaptopApplication addLaptopApplication() {
        return new AddLaptopApplication(
                deviceTypeService, deviceService,
                productService, userService, userRoleAssignmentService,
                laptopService
        );
    }

    @Bean
    public GetLaptopApplication getLaptopApplication() {
        return new GetLaptopApplication(
                laptopService, deviceService,
                productService
        );
    }

    @Bean
    public UpdateLaptopApplication updateLaptopApplication() {
        return new UpdateLaptopApplication(
                laptopService, userService,
                userRoleAssignmentService
        );
    }

    /*
     * SimCard
     */
    @Bean
    public AddSimCardApplication addSimCardApplication() {
        return new AddSimCardApplication(
                userService, userRoleAssignmentService,
                simCardService, mobileDeviceService
        );
    }

    @Bean
    public GetSimCardApplication getSimCardApplication() {
        return new GetSimCardApplication(
                simCardService, productService,
                deviceService, mobileDeviceService
        );
    }

    @Bean
    public UpdateSimCardApplication updateSimCardApplication() {
        return new UpdateSimCardApplication(
                simCardService, userService,
                userRoleAssignmentService
        );
    }

    @Bean
    public DeleteSimCardApplication deleteSimCardApplication() {
        return new DeleteSimCardApplication(
                simCardService, userService,
                userRoleAssignmentService
        );
    }

    /*
    Product image
    */

    @Bean
    public UploadProductImageApplication uploadProductImageApplication() {
        return new UploadProductImageApplication(
                productImageService, fileStorageService,
                userService, userRoleAssignmentService
        );
    }

    @Bean
    public DeleteProductImageApplication deleteProductImageApplication() {
        return new DeleteProductImageApplication(
                productImageService, fileStorageService
        );
    }

    @Bean
    public GetProductImagesApplication getProductImagesApplication() {
        return new GetProductImagesApplication(
                productImageService
        );
    }
}
