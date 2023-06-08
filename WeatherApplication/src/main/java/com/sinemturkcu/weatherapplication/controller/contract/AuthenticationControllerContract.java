package com.sinemturkcu.weatherapplication.controller.contract;

import com.sinemturkcu.weatherapplication.request.AuthenticationRequest;
import com.sinemturkcu.weatherapplication.request.RegisterRequest;
import com.sinemturkcu.weatherapplication.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public interface AuthenticationControllerContract {
    ResponseEntity<AuthenticationResponse> register(RegisterRequest request);
    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
