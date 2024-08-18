package com.davr7.quick_bank.resource.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StandardError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
