package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
  public ProductNotFoundException(String message) {
    super(message);
  }
}