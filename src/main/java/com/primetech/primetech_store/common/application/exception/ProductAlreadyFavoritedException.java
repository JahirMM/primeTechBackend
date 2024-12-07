package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class ProductAlreadyFavoritedException extends ConflictException {
    public ProductAlreadyFavoritedException(String message) {
        super(message);
    }
}
