package com.sinemturkcu.weatherapplication.dto;

import java.math.BigDecimal;

public record WeatherListMainItem(
        BigDecimal temp,
        BigDecimal feels_like,
        BigDecimal temp_min,
        BigDecimal temp_max,
        BigDecimal humidity

) {
}
