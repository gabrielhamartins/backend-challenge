package com.gabrielhamartins.backend_challenge.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gabrielhamartins.backend_challenge.controller.dto.TokenValidationDTO;
import com.gabrielhamartins.backend_challenge.exception.ClaimValidationException;
import com.gabrielhamartins.backend_challenge.service.TokenService;
import com.gabrielhamartins.backend_challenge.validator.ClaimValidator;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final ClaimValidator claimValidator;

    public TokenServiceImpl(ClaimValidator claimValidator) {
        this.claimValidator = claimValidator;
    }


    public Boolean validateToken(TokenValidationDTO token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token.token());
            claimValidator.validate(decodedJWT.getClaims());
        } catch (JWTDecodeException e){
            return false;
        } catch (ClaimValidationException e){
            return false;
        }
        return true;
    }
}
