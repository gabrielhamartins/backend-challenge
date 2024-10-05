package com.gabrielhamartins.backend_challenge.service;

import com.gabrielhamartins.backend_challenge.controller.dto.TokenValidationDTO;

public interface TokenService {

    Boolean validateToken(TokenValidationDTO token);
}
