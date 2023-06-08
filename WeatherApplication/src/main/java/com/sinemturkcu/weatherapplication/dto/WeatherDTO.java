package com.sinemturkcu.weatherapplication.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.math.BigDecimal;
import java.util.List;

@JacksonXmlRootElement(localName = "forecast")
public record WeatherDTO(
        String cod,
        BigDecimal message,
        Integer cnt,
        @JacksonXmlProperty(localName = "list")
        @JacksonXmlElementWrapper(localName = "list", useWrapping = true)
        List<WeatherListItemDTO> list,
        WeatherCityDTO city
) {
}
