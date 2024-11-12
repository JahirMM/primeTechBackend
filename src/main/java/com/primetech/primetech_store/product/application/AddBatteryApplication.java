package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.BatteryAlreadyExistsException;
import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.battery.AddBatteryRequestDTO;
import com.primetech.primetech_store.product.application.DTO.battery.BatteryDTO;
import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.product.domain.models.Battery;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddBatteryApplication {
    private final UserServiceInterface userService;
    private final ProductServiceInterface productService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final DeviceTypeServiceInterface deviceTypeService;
    private final DeviceServiceInterface deviceService;
    private final BatteryServiceInterface batteryService;

    @Transactional
    public BatteryDTO addBatteryApplication(AddBatteryRequestDTO request, String email, UUID productId) {
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

        if (batteryService.batteryExistsForDevice(device.getDeviceId())) {
            throw new BatteryAlreadyExistsException("Battery already exists for device");
        }

        Battery battery = createBattery(device, request);
        Battery saveBattery = batteryService.saveBattery(battery);

        return BatteryDTO.from(saveBattery);
    }

    private Battery createBattery(Device device, AddBatteryRequestDTO request) {
        return new Battery(
                device,
                request.getCapacity(),
                request.getType(),
                request.getWirelessCharging(),
                request.getFastCharging(),
                request.getMaxBatteryDuration()
        );
    }
}
