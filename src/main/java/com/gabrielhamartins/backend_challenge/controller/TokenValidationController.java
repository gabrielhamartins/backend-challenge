package com.gabrielhamartins.backend_challenge.controller;

import com.gabrielhamartins.backend_challenge.controller.dto.TokenValidationDTO;
import com.gabrielhamartins.backend_challenge.service.impl.TokenServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenValidationController extends BaseController{

    private final TokenServiceImpl tokenServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(TokenValidationController.class);

    public TokenValidationController(TokenServiceImpl tokenServiceImpl) {
        this.tokenServiceImpl = tokenServiceImpl;
    }

    @PostMapping("/tokens/validations")
    public ResponseEntity<Boolean> validateJwtToken(@RequestBody TokenValidationDTO token){
        logger.info("TokenValidationController.validateJwtToken() -> Request received.");
        return ResponseEntity.ok(tokenServiceImpl.validateToken(token));
    }
}
