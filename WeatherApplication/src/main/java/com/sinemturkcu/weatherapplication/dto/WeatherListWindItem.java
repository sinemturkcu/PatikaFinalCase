package com.sinemturkcu.weatherapplication.dto;

import java.math.BigDecimal;

public record WeatherListWindItem(
        BigDecimal speed,
        BigDecimal deg
) {
}
