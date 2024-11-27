package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.NotFoundException;

public class ProductImagesNotFoundException extends NotFoundException {
    public ProductImagesNotFoundException(String message) {
        super(message);
    }
}
