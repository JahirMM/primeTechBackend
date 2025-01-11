package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class OfferStatusException extends ConflictException {
    public OfferStatusException(String message) {
        super(message);
    }
}
