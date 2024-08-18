package com.davr7.quick_bank.resource.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
public class ValidationErrors extends StandardError {
    private Map<String, String> details = new HashMap<>();
    public ValidationErrors(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }
}
