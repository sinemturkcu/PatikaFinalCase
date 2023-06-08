package com.sinemturkcu.weatherapplication.controller.contract;

import com.sinemturkcu.weatherapplication.dto.WeatherDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserControllerContract {
    ResponseEntity<String> saveWeatherForUser(Long userId,String city);
    ResponseEntity<List<String>> getUserCities(Long userId);
    ResponseEntity<List<WeatherDTO>> getUserCitiesWeather( Long userId);
}
