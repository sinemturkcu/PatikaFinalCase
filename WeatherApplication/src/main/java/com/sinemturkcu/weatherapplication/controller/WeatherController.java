package com.sinemturkcu.weatherapplication.controller;


import com.sinemturkcu.weatherapplication.controller.contract.WeatherControllerContract;
import com.sinemturkcu.weatherapplication.dto.WeatherDTO;
import com.sinemturkcu.weatherapplication.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/v1/forecast/")
@CrossOrigin(origins="*")
public class WeatherController {

    private final WeatherControllerContract weatherControllerContract;

    public WeatherController(WeatherControllerContract weatherControllerContract) {
        this.weatherControllerContract = weatherControllerContract;
    }

    @Operation(summary = "Get a weather by city")
    @GetMapping("{city}")
    public WeatherDTO getWeatherForecast(@PathVariable("city") String city) {
       return weatherControllerContract.getWeatherForecast(city);
    }

    @Operation(summary = "Get a weather by coordinates")
    @GetMapping()
    public WeatherDTO getWeatherForecastWithCoordinates(@RequestParam BigDecimal lat, @RequestParam BigDecimal lon){
     return weatherControllerContract.getWeatherForecastWithCoordinates(lat, lon);
    }

    @Operation(summary = "Get a weather by zip code")
    @GetMapping("zipCode/{zipCode}")
    public WeatherDTO getWeatherForecastByZipCode(@PathVariable BigDecimal zipCode){
        return weatherControllerContract.getWeatherForecastByZipCode(zipCode);
    }

}

