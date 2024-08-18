package com.davr7.quick_bank.resource.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Getter
public class ErrorResponse extends StandardError {
    private String message;
    public ErrorResponse(Instant timestamp, Integer status, String error, String path, String message) {
        super(timestamp, status, error, path);
        this.message = message;
    }
}
