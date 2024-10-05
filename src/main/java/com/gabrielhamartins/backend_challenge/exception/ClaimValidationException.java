package com.gabrielhamartins.backend_challenge.exception;

import com.gabrielhamartins.backend_challenge.model.ClaimError;
import lombok.Getter;

import java.util.List;

@Getter
public class ClaimValidationException extends Exception {

    private final List<ClaimError> errors;

    public ClaimValidationException(List<ClaimError> errors) {
        super();
        this.errors = errors;
    }
}
