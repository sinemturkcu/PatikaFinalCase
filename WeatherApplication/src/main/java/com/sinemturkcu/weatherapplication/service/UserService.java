package com.sinemturkcu.weatherapplication.service;

import com.sinemturkcu.weatherapplication.dto.WeatherDTO;
import com.sinemturkcu.weatherapplication.entity.User;
import com.sinemturkcu.weatherapplication.entity.Weather;
import com.sinemturkcu.weatherapplication.exception.NotFoundException;
import com.sinemturkcu.weatherapplication.repository.UserRepository;
import com.sinemturkcu.weatherapplication.repository.WeatherRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WeatherRepository weatherRepository;
    private final WeatherService weatherService;
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public UserService(UserRepository repository, WeatherRepository weatherRepository, WeatherService weatherService) {
        this.weatherRepository = weatherRepository;
        this.userRepository = repository;
        this.weatherService = weatherService;
    }

    public void saveWeatherForUser(Long userId, String city) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            logger.error("User not found for ID: {}", userId);
            return new IllegalArgumentException("User not found");
        });

        Weather existingWeather = weatherRepository.findByUserAndCity(user, city);
        if (existingWeather != null) {
            throw new IllegalArgumentException("City already saved for the user");
        }

        Weather weather = new Weather();
        weather.setCity(city);
        weather.setUser(user);

        weatherRepository.save(weather);
        logger.info("Weather saved for user with ID: {}, city: {}", userId, city);
    }

    public List<String> getUserCities(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found for ID: {}", userId);
                    return new NotFoundException("User not found");
                });

        List<Weather> userWeatherList = weatherRepository.findByUserId(userId);
        List<String> userCities = userWeatherList.stream()
                .map(Weather::getCity)
                .collect(Collectors.toList());

        return userCities;
    }

    public List<WeatherDTO> getUserCitiesWeather(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found for ID: {}", userId);
                    return new NotFoundException("User not found");
                });

        List<String> cities = getUserCities(userId);
        List<WeatherDTO> weatherForecasts = new ArrayList<>();

        for (String city : cities) {
            try {
                WeatherDTO weatherDTO = weatherService.getWeatherForecast(city);
                weatherForecasts.add(weatherDTO);
                logger.info("Weather forecast retrieved successfully for user with ID: {}, city: {}", userId, city);
            } catch (Exception e) {
                logger.error("Error occurred while getting weather forecast for user with ID: {}, city: {}. Error: {}", userId, city, e.getMessage());
                // Handle the exception as per your application's requirements
            }
        }

        return weatherForecasts;
    }
}
