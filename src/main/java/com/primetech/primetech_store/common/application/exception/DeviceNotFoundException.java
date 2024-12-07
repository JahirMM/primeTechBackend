package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class DeviceNotFoundException extends NotFoundException {
    public DeviceNotFoundException(String message) {
        super(message);
    }
}
