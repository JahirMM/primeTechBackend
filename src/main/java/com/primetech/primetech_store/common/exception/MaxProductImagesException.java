package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.BadRequestException;

public class MaxProductImagesException extends BadRequestException {
    public MaxProductImagesException(String message) {
        super(message);
    }
}
