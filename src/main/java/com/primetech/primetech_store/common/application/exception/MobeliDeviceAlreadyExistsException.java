package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class MobeliDeviceAlreadyExistsException extends ConflictException {
    public MobeliDeviceAlreadyExistsException(String message) {
        super(message);
    }
}
