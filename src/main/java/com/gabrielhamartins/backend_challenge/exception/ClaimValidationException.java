package com.gabrielhamartins.backend_challenge.exception;

import com.gabrielhamartins.backend_challenge.model.ClaimError;
import lombok.Getter;

import java.util.List;

@Getter
public class ClaimValidationException extends Exception {

    private final List<ClaimError> errors;

    public ClaimValidationException(String message, List<ClaimError> errors) {
        super(message);
        this.errors = errors;
    }
}
