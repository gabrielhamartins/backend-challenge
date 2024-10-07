package com.gabrielhamartins.backend_challenge.controller;

import com.gabrielhamartins.backend_challenge.controller.dto.TokenValidationDTO;
import com.gabrielhamartins.backend_challenge.exception.ClaimValidationException;
import com.gabrielhamartins.backend_challenge.service.TokenService;
import com.gabrielhamartins.backend_challenge.service.impl.TokenServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenValidationController extends BaseController{

    private final TokenService tokenService;
    private static final Logger logger = LoggerFactory.getLogger(TokenValidationController.class);

    public TokenValidationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Operation(summary = "Validates a JWT Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Token validated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class)) })})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tokens/validations")
    public ResponseEntity<Boolean> validateJwtToken(@RequestBody TokenValidationDTO token) throws ClaimValidationException {
        logger.info("TokenValidationController.validateJwtToken() -> Request received.");
        return new ResponseEntity<>(tokenService.validateToken(token), HttpStatus.CREATED);
    }
}
