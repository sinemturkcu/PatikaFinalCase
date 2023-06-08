package com.sinemturkcu.weatherapplication.controller;
import com.sinemturkcu.weatherapplication.controller.UserController;
import com.sinemturkcu.weatherapplication.controller.contract.UserControllerContract;
import com.sinemturkcu.weatherapplication.dto.WeatherDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private UserController userController;

    @Mock
    private UserControllerContract userControllerContract;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userControllerContract);
    }

    @Test
    public void testSaveWeatherForUser() {
        // Prepare test data
        Long userId = 1L;
        String city = "London";

        // Set up mock behavior
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("City saved for the user");
        when(userControllerContract.saveWeatherForUser(userId, city)).thenReturn(expectedResponse);

        // Perform the test
        ResponseEntity<String> response = userController.saveWeatherForUser(userId, city);

        // Verify the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    public void testGetUserCities() {
        // Prepare test data
        Long userId = 1L;
        List<String> cities = List.of("London", "Paris", "New York");

        // Set up mock behavior
        ResponseEntity<List<String>> expectedResponse = ResponseEntity.ok(cities);
        when(userControllerContract.getUserCities(userId)).thenReturn(expectedResponse);

        // Perform the test
        ResponseEntity<List<String>> response = userController.getUserCities(userId);

        // Verify the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    public void testGetUserCitiesWeather() {
        // Prepare test data
        Long userId = 1L;
        List<WeatherDTO> weatherForecasts = new ArrayList<>(); // Create test weather forecasts

        // Set up mock behavior
        ResponseEntity<List<WeatherDTO>> expectedResponse = ResponseEntity.ok(weatherForecasts);
        when(userControllerContract.getUserCitiesWeather(userId)).thenReturn(expectedResponse);

        // Perform the test
        ResponseEntity<List<WeatherDTO>> response = userController.getUserCitiesWeather(userId);

        // Verify the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }
}
