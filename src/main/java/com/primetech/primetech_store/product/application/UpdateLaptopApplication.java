package com.primetech.primetech_store.product.application;

import com.primetech.primetech_store.common.exception.UserNotSellerException;
import com.primetech.primetech_store.product.application.DTO.laptop.LaptopDTO;
import com.primetech.primetech_store.product.application.DTO.laptop.LaptopRequestDTO;
import com.primetech.primetech_store.product.domain.interfaces.LaptopServiceInterface;
import com.primetech.primetech_store.product.domain.models.Laptop;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
public class UpdateLaptopApplication {
    private final LaptopServiceInterface laptopService;
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Transactional
    public LaptopDTO updateLaptopApplication(String email, UUID laptopId, LaptopRequestDTO request) {
        if (!userRoleAssignmentService.isSeller(userService.findUserInformationByEmail(email))) {
            throw new UserNotSellerException("The user is not a seller.");
        }

        Laptop laptop = laptopService.findLaptopByLaptopId(laptopId);
        laptop.setRam(request.getRam());
        laptop.setColor(request.getColor());
        laptop.setProcessor(request.getProcessor());
        laptop.setTouchscreen(request.getTouchscreen());
        laptop.setOperatingSystem(request.getOperatingSystem());
        laptop.setKeyboardLanguage(request.getKeyboardLanguage());
        laptop.setBacklitKeyboard(request.getBacklitKeyboard());
        laptop.setGraphicCard(request.getGraphicCard());
        laptop.setUsbPorts(request.getUsbPorts());
        laptop.setUsbCPorts(request.getUsbCPorts());
        laptop.setHdmiPorts(request.getHdmiPorts());
        laptop.setWithWifi(request.getWithWifi());
        laptop.setWithBluetooth(request.getWithBluetooth());
        laptop.setWithEthernetPort(request.getWithEthernetPort());
        laptop.setSsdStorage(request.getSsdStorage());
        laptop.setHddStorage(request.getHddStorage());
        laptop.setQuantitySpeakers(request.getQuantitySpeakers());
        laptop.setMicrophone(request.getMicrophone());

        Laptop saveLaptop = laptopService.saveLaptop(laptop);

        return LaptopDTO.from(saveLaptop);
    }
}
