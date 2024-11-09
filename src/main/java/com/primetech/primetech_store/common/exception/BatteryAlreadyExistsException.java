package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.ConflictException;

public class BatteryAlreadyExistsException extends ConflictException {
    public BatteryAlreadyExistsException(String message) {
        super(message);
    }
}
