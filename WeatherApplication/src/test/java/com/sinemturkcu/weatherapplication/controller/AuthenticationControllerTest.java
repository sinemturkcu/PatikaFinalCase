package com.sinemturkcu.weatherapplication.controller;

import com.sinemturkcu.weatherapplication.controller.contract.AuthenticationControllerContract;
import com.sinemturkcu.weatherapplication.controller.contract.impl.AuthenticationControllerContractImpl;
import com.sinemturkcu.weatherapplication.request.AuthenticationRequest;
import com.sinemturkcu.weatherapplication.request.RegisterRequest;
import com.sinemturkcu.weatherapplication.response.AuthenticationResponse;
import com.sinemturkcu.weatherapplication.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationControllerTest {

    private AuthenticationController authenticationController;
    private AuthenticationControllerContract authenticationControllerContract;
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        authenticationService = Mockito.mock(AuthenticationService.class);
        authenticationControllerContract = new AuthenticationControllerContractImpl(authenticationService);
        authenticationController = new AuthenticationController(authenticationControllerContract);
    }

    @Test
    public void testRegister() {
        // Prepare test data
        RegisterRequest registerRequest = new RegisterRequest();
        // Set up mock service response
        AuthenticationResponse mockResponse = new AuthenticationResponse();
        ResponseEntity<AuthenticationResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        Mockito.when(authenticationService.register(registerRequest)).thenReturn(mockResponse);

        // Execute the API call
        ResponseEntity<AuthenticationResponse> response = authenticationController.register(registerRequest);

        // Verify the response
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
        // Add additional assertions as needed
    }

    @Test
    public void testAuthenticate() {
        // Prepare test data
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        // Set up mock service response
        AuthenticationResponse mockResponse = new AuthenticationResponse();
        ResponseEntity<AuthenticationResponse> expectedResponse = ResponseEntity.ok(mockResponse);
        Mockito.when(authenticationService.authenticate(authenticationRequest)).thenReturn(mockResponse);

        // Execute the API call
        ResponseEntity<AuthenticationResponse> response = authenticationController.authenticate(authenticationRequest);

        // Verify the response
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
        // Add additional assertions as needed
    }

    @Test
    public void testRefreshToken() throws IOException {
        // Prepare test data
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Execute the API call
        authenticationController.refreshToken(request, response);

        // Verify that the service method is called
        Mockito.verify(authenticationService).refreshToken(request, response);
        // Add additional verifications as needed
    }
}
