package com.sinemturkcu.weatherapplication.controller.contract.impl;

import com.sinemturkcu.weatherapplication.controller.WeatherController;
import com.sinemturkcu.weatherapplication.controller.contract.WeatherControllerContract;
import com.sinemturkcu.weatherapplication.dto.WeatherDTO;
import com.sinemturkcu.weatherapplication.service.WeatherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WeatherControllerContractImpl implements WeatherControllerContract {

    private final WeatherService weatherService;
    private static final Logger logger = LogManager.getLogger(WeatherController.class);

    public WeatherControllerContractImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public WeatherDTO getWeatherForecast(String city) {
        logger.info("Request received to get weather forecast for city: {}", city);
        try {
            WeatherDTO weatherDTO = weatherService.getWeatherForecast(city);
            logger.info("Weather forecast retrieved successfully");
            return weatherDTO;
        } catch (Exception e) {
            logger.error("Error occurred while getting weather forecast for city: {}. Error: {}", city, e.getMessage());
            throw e;
        }
    }

    @Override
    public WeatherDTO getWeatherForecastWithCoordinates(BigDecimal lat, BigDecimal lon) {
        logger.info("Request received to get weather forecast with coordinates - Lat: {}, Lon: {}", lat, lon);
        try {
            WeatherDTO weatherDTO = weatherService.getWeatherForecastWithCoordinates(lat, lon);
            logger.info("Weather forecast retrieved successfully");
            return weatherDTO;
        } catch (Exception e) {
            logger.error("Error occurred while getting weather forecast with coordinates - Lat: {}, Lon: {}. Error: {}", lat, lon, e.getMessage());
            throw e;
        }
    }

    @Override
    public WeatherDTO getWeatherForecastByZipCode(BigDecimal zipCode) {
        logger.info("Request received to get weather forecast for zip code: {}", zipCode);
        try {
            WeatherDTO weatherDTO = weatherService.getWeatherForecastByZipCode(zipCode);
            logger.info("Weather forecast retrieved successfully");
            return weatherDTO;
        } catch (Exception e) {
            logger.error("Error occurred while getting weather forecast for zip code: {}. Error: {}", zipCode, e.getMessage());
            throw e;
        }
    }
}
