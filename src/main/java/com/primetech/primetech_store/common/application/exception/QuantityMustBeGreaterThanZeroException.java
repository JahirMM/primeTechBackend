package com.primetech.primetech_store.common.application.exception;

public class QuantityMustBeGreaterThanZeroException extends RuntimeException {
    public QuantityMustBeGreaterThanZeroException(String message) {
        super(message);
    }
}
