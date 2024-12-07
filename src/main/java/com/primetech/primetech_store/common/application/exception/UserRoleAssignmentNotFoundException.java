package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class UserRoleAssignmentNotFoundException extends NotFoundException {
    public UserRoleAssignmentNotFoundException(String message) {
        super(message);
    }
}
