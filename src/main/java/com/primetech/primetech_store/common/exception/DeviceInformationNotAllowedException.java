package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.NotFoundException;

public class DeviceInformationNotAllowedException extends NotFoundException {
    public DeviceInformationNotAllowedException(String message) {
        super(message);
    }
}