package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.product.domain.interfaces.*;
import com.primetech.primetech_store.product.domain.models.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductRelationshipService implements ProductRelationshipServiceInterface{
    private final CameraServiceInterface cameraService;
    private final ScreenServiceInterface screenService;
    private final BatteryServiceInterface batteryService;
    private final MobileDeviceServiceInterface mobileDeviceService;
    private final SimCardServiceInterface simCardService;
    private final LaptopServiceInterface laptopService;

    @Override
    public void deleteProductRelationships(UUID deviceId) {
        // Eliminar la cámara asociada al deviceId
        List<Camera> cameras = cameraService.findCameraInformationByDeviceId(deviceId);
        if (!cameras.isEmpty()) {
            for (Camera camera:cameras) {
                cameraService.deleteCameraByCameraId(camera.getCameraId());
            }
        }

        // Eliminar la pantalla asociada al deviceId
        List<Screen> screens = screenService.findScreenInformationByDeviceId(deviceId);
        if (!screens.isEmpty()) {
            screenService.deleteScreenByScreenId(screens.get(0).getScreenId());
        }

        // Eliminar la batería asociada al deviceId
        List<Battery> batteries = batteryService.findBatteryInformationByDeviceId(deviceId);
        if (!batteries.isEmpty()) {
            batteryService.deleteBatteryByBatteryId(batteries.get(0).getBatteryId());
        }

        // Eliminar MobileDevice y su SIM card si existe
        List<MobileDevice> mobileDevices = mobileDeviceService.findMobileDeviceInformationByDeviceId(deviceId);
        if (!mobileDevices.isEmpty()) {
            List<SimCard> simCards = simCardService.findSimCardInformationByMobileDevice(mobileDevices.get(0).getMobileDeviceId());
            if (!simCards.isEmpty()) {
                simCardService.deleteSimCardBySimCardId(simCards.get(0).getSimCardId());
            }
            mobileDeviceService.deleteMobileDeviceByMobileDeviceId(mobileDevices.get(0).getMobileDeviceId());
        }

        // Eliminar Laptop si existe
        List<Laptop> laptops = laptopService.findLaptopInformationByDeviceId(deviceId);
        if (!laptops.isEmpty()) {
            laptopService.deleteLaptopByLaptopId(laptops.get(0).getLaptopId());
        }
    }
}
