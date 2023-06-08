package com.sinemturkcu.weatherapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;


public record WeatherListItemDTO(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dt_txt,
        WeatherListMainItem main,
        List<WeatherListWeatherItem> weather

) {
}
