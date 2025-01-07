package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class ReviewNotFoundException extends NotFoundException {
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
