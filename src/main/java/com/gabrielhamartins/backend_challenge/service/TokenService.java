package com.gabrielhamartins.backend_challenge.service;

import com.gabrielhamartins.backend_challenge.controller.dto.TokenValidationDTO;
import com.gabrielhamartins.backend_challenge.exception.ClaimValidationException;

public interface TokenService {

    Boolean validateToken(TokenValidationDTO token) throws ClaimValidationException;
}
