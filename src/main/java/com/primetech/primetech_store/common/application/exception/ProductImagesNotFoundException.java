package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class ProductImagesNotFoundException extends NotFoundException {
    public ProductImagesNotFoundException(String message) {
        super(message);
    }
}
