package com.sinemturkcu.weatherapplication.controller;

import com.sinemturkcu.weatherapplication.controller.contract.UserControllerContract;
import com.sinemturkcu.weatherapplication.dto.WeatherDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> saveWeatherForUser(@PathVariable("userId") Long userId, @RequestParam("city") String city) {
        return userControllerContract.saveWeatherForUser(userId, city);
    }

    @GetMapping("/savedCities/{userId}")
    public ResponseEntity<List<String>> getUserCities(@PathVariable("userId") Long userId) {
        if (userControllerContract == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
        return userControllerContract.getUserCities(userId);
    }

    @GetMapping("/savedCitiesWeather/{userId}")
    public ResponseEntity<List<WeatherDTO>> getUserCitiesWeather(@PathVariable("userId") Long userId) {
        if (userControllerContract == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
        return userControllerContract.getUserCitiesWeather(userId);
    }
}
