package com.primetech.primetech_store.common.exception;

public class QuantityMustBeGreaterThanZeroException extends RuntimeException {
    public QuantityMustBeGreaterThanZeroException(String message) {
        super(message);
    }
}
