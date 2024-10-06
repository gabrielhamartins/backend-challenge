package com.gabrielhamartins.backend_challenge.controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielhamartins.backend_challenge.controller.dto.TokenValidationDTO;
import com.gabrielhamartins.backend_challenge.exception.ClaimValidationException;
import com.gabrielhamartins.backend_challenge.service.impl.TokenServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static com.gabrielhamartins.backend_challenge.util.TestUtil.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TokenValidationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TokenValidationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TokenServiceImpl tokenService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturn2xxWhenTokenValidationController_validateJwt() throws Exception {
        Mockito.when(tokenService.validateToken(ArgumentMatchers.any())).thenReturn(true);
        mockMvc.perform(post(API_V1_BASE_URL + "/tokens/validations")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TokenValidationDTO(VALID_TOKEN))))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void shouldReturn400WhenTokenValidationController_validateJwt() throws Exception {
        Mockito.when(tokenService.validateToken(ArgumentMatchers.any())).thenThrow(JWTDecodeException.class);
        mockMvc.perform(post(API_V1_BASE_URL + "/tokens/validations")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TokenValidationDTO(INVALID_TOKEN_FORMAT))))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void shouldReturn412WhenTokenValidationController_validateJwt() throws Exception {
        Mockito.when(tokenService.validateToken(ArgumentMatchers.any())).thenThrow(new ClaimValidationException("", List.of()));
        mockMvc.perform(post(API_V1_BASE_URL + "/tokens/validations")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TokenValidationDTO(INVALID_TOKEN_CLAIM_NAME_WITH_DIGITS))))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}