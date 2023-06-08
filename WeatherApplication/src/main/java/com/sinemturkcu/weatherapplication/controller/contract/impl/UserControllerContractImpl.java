package com.sinemturkcu.weatherapplication.controller.contract.impl;

import com.sinemturkcu.weatherapplication.controller.UserController;
import com.sinemturkcu.weatherapplication.controller.contract.UserControllerContract;
import com.sinemturkcu.weatherapplication.dto.WeatherDTO;
import com.sinemturkcu.weatherapplication.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserControllerContractImpl implements UserControllerContract {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    public UserControllerContractImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<String> saveWeatherForUser(Long userId, String city) {
        logger.info("Request received to save weather for user - User ID: {}, City: {}", userId, city);
        try {
            userService.saveWeatherForUser(userId, city);
            logger.info("Weather saved successfully for user");
            return ResponseEntity.ok("City saved for the user");
        } catch (Exception e) {
            logger.error("Error occurred while saving weather for user - User ID: {}, City: {}. Error: {}", userId, city, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save city for the user");
        }
    }

    @Override
    public ResponseEntity<List<String>> getUserCities(Long userId) {
        logger.info("Request received to get cities for user - User ID: {}", userId);
        try {
            List<String> cities = userService.getUserCities(userId);
            logger.info("Cities retrieved successfully for user");
            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            logger.error("Error occurred while getting cities for user - User ID: {}. Error: {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @Override
    public ResponseEntity<List<WeatherDTO>> getUserCitiesWeather(Long userId) {
        logger.info("Request received to get weather forecasts for user - User ID: {}", userId);
        try {
            List<WeatherDTO> weatherForecasts = userService.getUserCitiesWeather(userId);
            logger.info("Weather forecasts retrieved successfully for user");
            return ResponseEntity.ok(weatherForecasts);
        } catch (Exception e) {
            logger.error("Error occurred while getting weather forecasts for user - User ID: {}. Error: {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
