package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.ConflictException;

public class ProductAlreadyFavoritedException extends ConflictException {
    public ProductAlreadyFavoritedException(String message) {
        super(message);
    }
}
