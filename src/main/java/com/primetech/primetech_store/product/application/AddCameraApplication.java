package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.AddCameraRequestDTO;
import com.primetech.primetech_store.product.application.DTO.CameraDTO;
import com.primetech.primetech_store.product.domain.interfaces.CameraServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceTypeServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Camera;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddCameraApplication {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final DeviceTypeServiceInterface deviceTypeService;
    private final DeviceServiceInterface deviceService;
    private final CameraServiceInterface cameraService;

    @Transactional
    public CameraDTO addMobileDeviceApplication(AddCameraRequestDTO request, String email, UUID productId) {
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        if (!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }

        DeviceType deviceMobileType = deviceTypeService.findDeviceTypeByTypeName("mobile");
        DeviceType deviceLaptopType = deviceTypeService.findDeviceTypeByTypeName("laptop");

        Device device = deviceService.findDeviceByMultipleDeviceTypes(productId, deviceMobileType.getDeviceTypeId(), deviceLaptopType.getDeviceTypeId());

        Camera camera = createCamera(device, request);
        Camera saveCamera = cameraService.saveCamera(camera);

        return CameraDTO.from(saveCamera);
    }

    private Camera createCamera(Device device, AddCameraRequestDTO request) {
        return new Camera(
                device,
                request.getType(),
                request.getResolution(),
                request.getAperture(),
                request.getOpticalZoom(),
                request.getDigitalZoom(),
                request.getFeature()
        );
    }
}
