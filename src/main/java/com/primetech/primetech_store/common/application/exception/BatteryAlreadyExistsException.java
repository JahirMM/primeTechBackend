package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class BatteryAlreadyExistsException extends ConflictException {
    public BatteryAlreadyExistsException(String message) {
        super(message);
    }
}
