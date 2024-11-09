package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.ConflictException;

public class MobeliDeviceAlreadyExistsException extends ConflictException {
    public MobeliDeviceAlreadyExistsException(String message) {
        super(message);
    }
}
