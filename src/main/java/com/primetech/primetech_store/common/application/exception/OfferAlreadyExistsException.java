package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class OfferAlreadyExistsException extends ConflictException {
    public OfferAlreadyExistsException(String message) {
        super(message);
    }
}
