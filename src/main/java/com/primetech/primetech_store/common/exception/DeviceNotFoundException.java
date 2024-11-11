package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.NotFoundException;

public class DeviceNotFoundException extends NotFoundException {
    public DeviceNotFoundException(String message) {
        super(message);
    }
}
