package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class LaptopAlreadyHasCameraException extends ConflictException {
    public LaptopAlreadyHasCameraException(String message) {
        super(message);
    }
}
