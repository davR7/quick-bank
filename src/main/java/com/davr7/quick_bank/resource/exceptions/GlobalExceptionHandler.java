package com.davr7.quick_bank.resource.exceptions;

import com.davr7.quick_bank.service.exceptions.AccountExistsException;
import com.davr7.quick_bank.service.exceptions.AccountNotFoundException;
import com.davr7.quick_bank.service.exceptions.CustomerExistsException;
import com.davr7.quick_bank.service.exceptions.CustomerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomerNotFoundException.class, AccountNotFoundException.class})
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(Exception ex, HttpServletRequest req) {
        int status = HttpStatus.NOT_FOUND.value();
        String error = "Resource Not Found";

        ErrorResponse errResponse = new ErrorResponse(Instant.now(), status, error, req.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(status).body(errResponse);
    }

    @ExceptionHandler(value = {CustomerExistsException.class, AccountExistsException.class})
    public ResponseEntity<ErrorResponse> handlerResourceExistsException(Exception ex, HttpServletRequest req) {
        int status = HttpStatus.CONFLICT.value();
        String error = "Resource Exists";

        ErrorResponse errResponse = new ErrorResponse(Instant.now(), status, error, req.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(status).body(errResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ValidationErrors> handlerValidationException(MethodArgumentNotValidException ex, HttpServletRequest req) {
        int status = HttpStatus.BAD_REQUEST.value();
        String error = "validation is failed";
        ValidationErrors errResponse = new ValidationErrors(Instant.now(), status, error, req.getRequestURI());

        ex.getBindingResult().getAllErrors().forEach(err -> {
            String field = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            errResponse.getDetails().put(field, message);
        });

        return ResponseEntity.status(status).body(errResponse);
    }
}
