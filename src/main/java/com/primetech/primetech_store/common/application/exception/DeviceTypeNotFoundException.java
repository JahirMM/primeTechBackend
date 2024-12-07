package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class DeviceTypeNotFoundException extends NotFoundException {
    public DeviceTypeNotFoundException(String message) {
        super(message);
    }
}
