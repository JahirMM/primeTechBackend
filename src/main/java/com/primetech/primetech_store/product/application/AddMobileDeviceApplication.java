package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.mobileDevice.MobileDeviceRequestDTO;
import com.primetech.primetech_store.product.application.DTO.mobileDevice.MobileDeviceDTO;
import com.primetech.primetech_store.product.domain.interfaces.DeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.DeviceTypeServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.MobileDeviceServiceInterface;
import com.primetech.primetech_store.product.domain.interfaces.ProductServiceInterface;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.domain.models.MobileDevice;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddMobileDeviceApplication {
    private final DeviceTypeServiceInterface deviceTypeService;
    private final DeviceServiceInterface deviceService;
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final MobileDeviceServiceInterface mobileDeviceService;

    @Transactional
    public MobileDeviceDTO addMobileDeviceApplication(MobileDeviceRequestDTO request, UUID productId, String email) {
        // validar si el usuario es vendedor
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        // validar si el producto existe
        if (!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }

        // validar si el producto es de type mobile y ademas capturamos el deviceTypeId
        DeviceType deviceType = deviceTypeService.findDeviceTypeByTypeName("mobile");

        // buscar el device
        Device device = deviceService.findDevice(productId, deviceType.getDeviceTypeId(), "mobile_type");

        //creamos el mobileDevice donde necesitamos el Device y los datos del request
        MobileDevice mobileDevice = createMobileDevice(device, request);

        // guardamos el mobileDevice
        MobileDevice savedMobileDevice = mobileDeviceService.saveMobileDevice(mobileDevice);

        return MobileDeviceDTO.from(savedMobileDevice);
    }

    private MobileDevice createMobileDevice(Device device, MobileDeviceRequestDTO request) {
        return new MobileDevice(
                device,
                request.getInternalMemory(),
                request.getInternalMemoryType(),
                request.getRam(),
                request.getColor(),
                request.getProcessor(),
                request.getOperatingSystem(),
                request.getIpRating(),
                request.isSplashResistant(),
                request.isDustResistant(),
                request.isWaterResistant()
        );
    }
}
