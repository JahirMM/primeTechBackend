package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.BadRequestException;

public class MaxProductImagesException extends BadRequestException {
    public MaxProductImagesException(String message) {
        super(message);
    }
}
