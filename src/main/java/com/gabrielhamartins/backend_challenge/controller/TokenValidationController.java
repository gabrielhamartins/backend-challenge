package com.gabrielhamartins.backend_challenge.controller;

import com.gabrielhamartins.backend_challenge.controller.dto.TokenValidationDTO;
import com.gabrielhamartins.backend_challenge.service.impl.TokenServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenValidationController extends BaseController{

    private final TokenServiceImpl tokenServiceImpl;

    public TokenValidationController(TokenServiceImpl tokenServiceImpl) {
        this.tokenServiceImpl = tokenServiceImpl;
    }

    @PostMapping("/tokens/validations")
    public ResponseEntity<Boolean> validateJwtToken(@RequestBody TokenValidationDTO token){
        return ResponseEntity.ok(tokenServiceImpl.validateToken(token));
    }
}
