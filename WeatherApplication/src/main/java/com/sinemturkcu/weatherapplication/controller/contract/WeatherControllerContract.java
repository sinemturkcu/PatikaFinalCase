package com.sinemturkcu.weatherapplication.controller.contract;

import com.sinemturkcu.weatherapplication.dto.WeatherDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface WeatherControllerContract {
    WeatherDTO getWeatherForecast(String city);
    WeatherDTO getWeatherForecastWithCoordinates(BigDecimal lat, BigDecimal lon);
    WeatherDTO getWeatherForecastByZipCode(BigDecimal zipCode);
}
