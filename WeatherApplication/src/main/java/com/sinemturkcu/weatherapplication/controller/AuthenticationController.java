package com.sinemturkcu.weatherapplication.controller;

import com.sinemturkcu.weatherapplication.controller.contract.AuthenticationControllerContract;
import com.sinemturkcu.weatherapplication.request.AuthenticationRequest;
import com.sinemturkcu.weatherapplication.request.RegisterRequest;
import com.sinemturkcu.weatherapplication.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationControllerContract authenticationControllerContract;

    public AuthenticationController(AuthenticationControllerContract authenticationControllerContract) {
        this.authenticationControllerContract = authenticationControllerContract;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return authenticationControllerContract.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationControllerContract.authenticate(request);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationControllerContract.refreshToken(request, response);
    }
}
