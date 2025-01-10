package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class OfferNotFoundException extends NotFoundException {
    public OfferNotFoundException(String message) {
        super(message);
    }
}
