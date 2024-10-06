package com.gabrielhamartins.backend_challenge.service;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.gabrielhamartins.backend_challenge.controller.dto.TokenValidationDTO;
import com.gabrielhamartins.backend_challenge.exception.ClaimValidationException;
import com.gabrielhamartins.backend_challenge.service.impl.TokenServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gabrielhamartins.backend_challenge.util.TestUtil.*;

public class TokenServiceImplTest {

    private final TokenService tokenService = new TokenServiceImpl();

    @Test
    public void shouldReturnTrueWhenTokenIsValid() throws ClaimValidationException {
        Assertions.assertTrue(tokenService.validateToken(new TokenValidationDTO(VALID_TOKEN)));
    }

    @Test
    public void shouldReturnFalseWhenTokenFormatIsInvalid() throws ClaimValidationException {
        Assertions.assertThrows(JWTDecodeException.class, () -> tokenService.validateToken(new TokenValidationDTO(INVALID_TOKEN_FORMAT)));
    }

    @Test
    public void shouldReturnFalseWhenTokenClaimNameHaveDigits() throws ClaimValidationException {
        Assertions.assertThrows(ClaimValidationException.class, () -> tokenService.validateToken(new TokenValidationDTO(INVALID_TOKEN_CLAIM_NAME_WITH_DIGITS)));
    }

    @Test
    public void shouldReturnFalseWhenTokenClaimSizeAreInvalid() throws ClaimValidationException {
        Assertions.assertThrows(ClaimValidationException.class, () -> tokenService.validateToken(new TokenValidationDTO(INVALID_TOKEN_CLAIMS_SIZE)));
    }

    @Test
    public void shouldReturnFalseWhenTokenClaimSeedAreNotPrime() throws ClaimValidationException {
        Assertions.assertThrows(ClaimValidationException.class, () -> tokenService.validateToken(new TokenValidationDTO(INVALID_TOKEN_CLAIM_SEED_NOT_PRIME)));
    }

    @Test
    public void shouldReturnFalseWhenTokenClaimsAreInvalid() throws ClaimValidationException {
        Assertions.assertThrows(ClaimValidationException.class, () -> tokenService.validateToken(new TokenValidationDTO(INVALID_TOKEN_CLAIMS)));
    }

    @Test
    public void shouldReturnFalseWhenTokenClaimNameAreTooBig() throws ClaimValidationException {
        Assertions.assertThrows(ClaimValidationException.class, () -> tokenService.validateToken(new TokenValidationDTO(INVALID_TOKEN_CLAIM_NAME_SIZE)));
    }

    @Test
    public void shouldReturnFalseWhenTokenClaimRoleAreInvalid() throws ClaimValidationException {
        Assertions.assertThrows(ClaimValidationException.class, () -> tokenService.validateToken(new TokenValidationDTO(INVALID_TOKEN_CLAIM_ROLE)));
    }
}
