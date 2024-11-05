package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.DTO.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(UserAlreadyHasRoleException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyHasRoleException(UserAlreadyHasRoleException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleRoleNotFoundException(RoleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(UserRoleAssignmentNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserRoleAssignmentNotFoundException( UserRoleAssignmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put("message", error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserImageNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserImageNotFoundException(UserImageNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponseDTO> handleFileStorageException(FileStorageException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(InvalidFileFormatException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidFileFormatException(InvalidFileFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(UserImageDeletionException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserImageDeletionException(UserImageDeletionException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(InvalidFieldFormatException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidFieldFormatException(InvalidFieldFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO("Unexpected error occurred"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO("Invalid argument provided"));
    }
}
