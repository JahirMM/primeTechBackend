package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.NotFoundException;

public class MobileDeviceNotFoundException extends NotFoundException {
    public MobileDeviceNotFoundException(String message) {
        super(message);
    }
}
