package com.primetech.primetech_store.product.infraestructure.services;

import com.primetech.primetech_store.common.exception.DeviceTypeNotFoundException;
import com.primetech.primetech_store.product.domain.interfaces.DeviceTypeServiceInterface;
import com.primetech.primetech_store.product.domain.models.DeviceType;
import com.primetech.primetech_store.product.infraestructure.repositories.DeviceTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class DeviceTypeService implements DeviceTypeServiceInterface {
    private final DeviceTypeRepository deviceTypeRepository;
    @Override
    public DeviceType findDeviceTypeByTypeName(String typeName) {
        Optional<DeviceType> deviceTypeOptional = deviceTypeRepository.findDeviceTypeByTypeName(typeName);

        if (deviceTypeOptional.isEmpty()) {
            throw new DeviceTypeNotFoundException("Device type not found");
        }

        return deviceTypeOptional.get();
    }
}
