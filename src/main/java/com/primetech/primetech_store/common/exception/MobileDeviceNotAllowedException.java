package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.NotFoundException;

public class MobileDeviceNotAllowedException extends NotFoundException {
    public MobileDeviceNotAllowedException(String message) {
        super(message);
    }
}
