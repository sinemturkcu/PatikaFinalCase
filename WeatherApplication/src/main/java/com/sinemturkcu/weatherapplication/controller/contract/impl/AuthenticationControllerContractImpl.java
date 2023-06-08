package com.sinemturkcu.weatherapplication.controller.contract.impl;

import com.sinemturkcu.weatherapplication.controller.AuthenticationController;
import com.sinemturkcu.weatherapplication.controller.contract.AuthenticationControllerContract;
import com.sinemturkcu.weatherapplication.request.AuthenticationRequest;
import com.sinemturkcu.weatherapplication.request.RegisterRequest;
import com.sinemturkcu.weatherapplication.response.AuthenticationResponse;
import com.sinemturkcu.weatherapplication.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationControllerContractImpl implements AuthenticationControllerContract {

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationService service;

    public AuthenticationControllerContractImpl(AuthenticationService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        logger.info("Received register request for email: {}", request.getEmail());
        try {
            AuthenticationResponse response = service.register(request);
            logger.info("User registered successfully: {}", request.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Failed to register user: {}", request.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        logger.info("Received authentication request for email: {}", request.getEmail());
        try {
            AuthenticationResponse response = service.authenticate(request);
            logger.info("User authenticated successfully: {}", request.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Authentication failed for username: {}", request.getEmail(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Received refresh token request");
        try {
            service.refreshToken(request, response);
            logger.info("Refresh token generated successfully");
        } catch (Exception e) {
            logger.error("Failed to generate refresh token", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
