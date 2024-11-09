package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.ProductNotFoundException;
import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.*;
import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.product.domain.models.Device;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.domain.models.Laptop;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import com.primetech.primetech_store.user.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class AddLaptopApplication {
    private final DeviceTypeServiceInterface deviceTypeService;
    private final DeviceServiceInterface deviceService;
    private final ProductServiceInterface productService;
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final LaptopServiceInterface laptopService;

    @Transactional
    public LaptopDTO addLaptopApplication(AddLaptopRequestDTO request, UUID productId, String email) {
        User user = userService.findUserInformationByEmail(email);

        if (!userRoleAssignmentService.isSeller(user)) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        if (!productService.existsProductByProductId(productId)) {
            throw new ProductNotFoundException("Product not found");
        }

        DeviceType deviceType = deviceTypeService.findDeviceTypeByTypeName("laptop");

        Device device = deviceService.findDevice(productId, deviceType.getDeviceTypeId(), "laptop");

        Laptop laptop = createLaptop(device, request);

        Laptop savedLaptop = laptopService.saveLaptop(laptop);

        return LaptopDTO.from(savedLaptop);
    }

    private Laptop createLaptop(Device device, AddLaptopRequestDTO request) {
        return new Laptop(
                device,
                request.getRam(),
                request.getColor(),
                request.getProcessor(),
                request.getTouchscreen(),
                request.getOperatingSystem(),
                request.getKeyboardLanguage(),
                request.getBacklitKeyboard(),
                request.getGraphicCard(),
                request.getUsbPorts(),
                request.getUsbCPorts(),
                request.getHdmiPorts(),
                request.getWithWifi(),
                request.getWithBluetooth(),
                request.getWithEthernetPort(),
                request.getSsdStorage(),
                request.getHddStorage(),
                request.getQuantitySpeakers(),
                request.getMicrophone()
        );
    }
}
