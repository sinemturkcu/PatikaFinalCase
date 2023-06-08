package com.sinemturkcu.weatherapplication.service;

import com.sinemturkcu.weatherapplication.dto.WeatherDTO;
import com.sinemturkcu.weatherapplication.entity.Weather;
import com.sinemturkcu.weatherapplication.exception.WeatherForecastException;
import com.sinemturkcu.weatherapplication.repository.WeatherRepository;
import com.sinemturkcu.weatherapplication.service.base.BaseEntityService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class WeatherService extends BaseEntityService<Weather, WeatherRepository> {
    @Value("${weatherApiKey}")
    private String weatherApiKey;

    @Value("${weatherApiUrl}")
    private String weatherApiUrl;

    private static final Logger logger = LogManager.getLogger(WeatherService.class);

    public WeatherService(WeatherRepository repository) {
        super(repository);
    }

    public WeatherDTO getWeatherForecast(String city) {
        logger.info("Request to getWeatherForecast method: city={}", city);
        String forecastUrl = weatherApiUrl + "?units=metric&q=" + city + "&appid=" + weatherApiKey;
        RestTemplate restTemplate = new RestTemplate();
        try {
            WeatherDTO weatherForecastResponse = restTemplate.getForObject(forecastUrl, WeatherDTO.class);
            return weatherForecastResponse;
        } catch (Exception e) {
            logger.error("Error occurred while getting weather forecast for city: {}. Error: {}", city, e.getMessage());
            throw new WeatherForecastException("Failed to retrieve weather forecast for city: " + city);
        }
    }

    public WeatherDTO getWeatherForecastByZipCode(BigDecimal zipCode) {
        logger.info("Request to getWeatherForecastByZipCode method: zipCode={}", zipCode);
        String forecastUrl = weatherApiUrl + "?units=metric&zip=" + zipCode + "&appid=" + weatherApiKey;
        RestTemplate restTemplate = new RestTemplate();
        try {
            WeatherDTO weatherForecastResponse = restTemplate.getForObject(forecastUrl, WeatherDTO.class);
            return weatherForecastResponse;
        } catch (Exception e) {
            logger.error("Error occurred while getting weather forecast for zip code: {}. Error: {}", zipCode, e.getMessage());
            throw new WeatherForecastException("Failed to retrieve weather forecast for zip code: " + zipCode);
        }
    }

    public WeatherDTO getWeatherForecastWithCoordinates(BigDecimal lat, BigDecimal lon) {
        logger.info("Request to getWeatherForecastWithCoordinates method: lat={}, lon={}", lat, lon);
        String forecastUrl = weatherApiUrl + "?units=metric&lat=" + lat + "&lon=" + lon + "&appid=" + weatherApiKey;
        RestTemplate restTemplate = new RestTemplate();
        try {
            WeatherDTO weatherForecastResponse = restTemplate.getForObject(forecastUrl, WeatherDTO.class);
            return weatherForecastResponse;
        } catch (Exception e) {
            logger.error("Error occurred while getting weather forecast for coordinates - lat: {}, lon: {}. Error: {}", lat, lon, e.getMessage());
            throw new WeatherForecastException("Failed to retrieve weather forecast for coordinates - lat: " + lat + ", lon: " + lon);
        }
    }
}
