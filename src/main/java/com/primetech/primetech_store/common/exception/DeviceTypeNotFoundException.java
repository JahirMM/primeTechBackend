package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.NotFoundException;

public class DeviceTypeNotFoundException extends NotFoundException {
    public DeviceTypeNotFoundException(String message) {
        super(message);
    }
}
