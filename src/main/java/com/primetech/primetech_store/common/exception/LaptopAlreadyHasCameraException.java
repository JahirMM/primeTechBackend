package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.ConflictException;

public class LaptopAlreadyHasCameraException extends ConflictException {
    public LaptopAlreadyHasCameraException(String message) {
        super(message);
    }
}
