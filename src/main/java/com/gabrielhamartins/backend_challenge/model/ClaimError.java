package com.gabrielhamartins.backend_challenge.model;

public record ClaimError (
        String claim,
        String value,
        String error
){

}
