package com.sinemturkcu.weatherapplication.controller;

import com.sinemturkcu.weatherapplication.controller.contract.WeatherControllerContract;
import com.sinemturkcu.weatherapplication.controller.contract.impl.WeatherControllerContractImpl;
import com.sinemturkcu.weatherapplication.dto.*;
import com.sinemturkcu.weatherapplication.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherControllerTest {

    private WeatherController weatherController;
    private WeatherControllerContract weatherControllerContract;
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        weatherService = Mockito.mock(WeatherService.class);
        weatherControllerContract = new WeatherControllerContractImpl(weatherService);
        weatherController = new WeatherController(weatherControllerContract);
    }

    @Test
    public void testWeatherForecast() {
        // Prepare the expected response
        WeatherCityDTO city = new WeatherCityDTO("London", "UK");

        List<WeatherListItemDTO> forecastList = new ArrayList<>();

        BigDecimal temp = new BigDecimal("20.5");
        BigDecimal feelsLike = new BigDecimal("15.2");
        BigDecimal tempMin = new BigDecimal("18.0");
        BigDecimal tempMax = new BigDecimal("23.5");
        BigDecimal humidity = new BigDecimal("80.0");
        WeatherListMainItem mainItem = new WeatherListMainItem(temp, feelsLike, tempMin, tempMax, humidity);

        List<WeatherListWeatherItem> weatherItems = new ArrayList<>();
        weatherItems.add(new WeatherListWeatherItem("Cloudy", "Cloudy weather","icon"));
        WeatherListItemDTO listItem = new WeatherListItemDTO(LocalDateTime.now(), mainItem, weatherItems);
        forecastList.add(listItem);

        WeatherDTO expectedResponse = new WeatherDTO("200", new BigDecimal("0.01"), 1, forecastList, city);


        Mockito.when(weatherService.getWeatherForecast(Mockito.anyString()))
                .thenReturn(expectedResponse);


        WeatherDTO actualResponse = weatherController.getWeatherForecast(city.name());

        // Compare the expectedResponse with the actualResponse
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testWeatherForecastWithCoordinates() {
        // Prepare the expected response
        WeatherCityDTO city = new WeatherCityDTO("London", "UK");

        List<WeatherListItemDTO> forecastList = new ArrayList<>();

        BigDecimal temp = new BigDecimal("20.5");
        BigDecimal feelsLike = new BigDecimal("15.2");
        BigDecimal tempMin = new BigDecimal("18.0");
        BigDecimal tempMax = new BigDecimal("23.5");
        BigDecimal humidity = new BigDecimal("80.0");
        WeatherListMainItem mainItem = new WeatherListMainItem(temp, feelsLike, tempMin, tempMax, humidity);

        List<WeatherListWeatherItem> weatherItems = new ArrayList<>();
        weatherItems.add(new WeatherListWeatherItem("Cloudy", "Cloudy weather","icon"));
        WeatherListItemDTO listItem = new WeatherListItemDTO(LocalDateTime.now(), mainItem, weatherItems);
        forecastList.add(listItem);

        WeatherDTO expectedResponse = new WeatherDTO("200", new BigDecimal("0.01"), 1, forecastList, city);

        // Mock the weatherService to return the expectedResponse
        Mockito.when(weatherService.getWeatherForecastWithCoordinates(Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class)))
                .thenReturn(expectedResponse);

        // Perform the actual test
        BigDecimal latitude = new BigDecimal("51.5074");
        BigDecimal longitude = new BigDecimal("-0.1278");
        WeatherDTO actualResponse = weatherController.getWeatherForecastWithCoordinates(latitude, longitude);

        // Compare the expectedResponse with the actualResponse
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testWeatherForecastByZipCode() {
        // Prepare the expected response
        WeatherCityDTO city = new WeatherCityDTO("London", "UK");

        List<WeatherListItemDTO> forecastList = new ArrayList<>();

        BigDecimal temp = new BigDecimal("20.5");
        BigDecimal feelsLike = new BigDecimal("15.2");
        BigDecimal tempMin = new BigDecimal("18.0");
        BigDecimal tempMax = new BigDecimal("23.5");
        BigDecimal humidity = new BigDecimal("80.0");
        WeatherListMainItem mainItem = new WeatherListMainItem(temp, feelsLike, tempMin, tempMax, humidity);

        List<WeatherListWeatherItem> weatherItems = new ArrayList<>();
        weatherItems.add(new WeatherListWeatherItem("Cloudy", "Cloudy weather","icon"));
        WeatherListItemDTO listItem = new WeatherListItemDTO(LocalDateTime.now(), mainItem, weatherItems);
        forecastList.add(listItem);

        WeatherDTO expectedResponse = new WeatherDTO("200", new BigDecimal("0.01"), 1, forecastList, city);

        // Mock the weatherService to return the expectedResponse
        Mockito.when(weatherService.getWeatherForecastByZipCode(Mockito.any(BigDecimal.class)))
                .thenReturn(expectedResponse);

        // Perform the actual test
        BigDecimal zipCode = new BigDecimal("12345");
        WeatherDTO actualResponse = weatherController.getWeatherForecastByZipCode(zipCode);

        // Compare the expectedResponse with the actualResponse
        assertEquals(expectedResponse, actualResponse);
    }


}
