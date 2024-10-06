package com.gabrielhamartins.backend_challenge.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gabrielhamartins.backend_challenge.controller.dto.TokenValidationDTO;
import com.gabrielhamartins.backend_challenge.exception.ClaimValidationException;
import com.gabrielhamartins.backend_challenge.service.TokenService;
import com.gabrielhamartins.backend_challenge.validator.ClaimValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final ClaimValidator claimValidator = new ClaimValidator();

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    public Boolean validateToken(TokenValidationDTO tokenDTO) {
        logger.info("TokenServiceImpl.validateToken() -> Started with token {} .", tokenDTO.token());
        try {
            DecodedJWT decodedJWT = JWT.decode(tokenDTO.token());
            claimValidator.validate(decodedJWT.getClaims());
        } catch (JWTDecodeException e){
            logger.error(e.getMessage(), e);
            return false;
        } catch (ClaimValidationException e){
            logger.error(e.getErrors().toString(), e);
            return false;
        }

        logger.info("TokenServiceImpl.validateToken() finished succesfully.");
        return true;
    }
}
