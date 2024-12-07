package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class DeviceInformationNotAllowedException extends NotFoundException {
    public DeviceInformationNotAllowedException(String message) {
        super(message);
    }
}
