package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class MobileDeviceNotFoundException extends NotFoundException {
    public MobileDeviceNotFoundException(String message) {
        super(message);
    }
}
