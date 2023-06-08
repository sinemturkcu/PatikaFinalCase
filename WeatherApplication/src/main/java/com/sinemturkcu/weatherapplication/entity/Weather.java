package com.sinemturkcu.weatherapplication.entity;

import com.sinemturkcu.weatherapplication.dto.WeatherListCloudItem;
import com.sinemturkcu.weatherapplication.dto.WeatherListItemDTO;
import com.sinemturkcu.weatherapplication.dto.WeatherListMainItem;
import com.sinemturkcu.weatherapplication.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

@Entity
@Table(name = "WEATHER")
@NoArgsConstructor
@Getter
@Setter
public class Weather extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="WEATHER_ID_SEQ"
    )
    private Long id;

    @Column(name = "CITY")
    private String city;

    /*
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "LOCAL_DATE_TIME")
    private LocalDateTime timestamp;

    @Column(name = "TEMPERATURE")
    private BigDecimal temperature;

    @Column(name = "FEELS_LIKE")
    private BigDecimal feelsDegree;

    @Column(name = "MIN_TEMP")
    private BigDecimal minTemperature;

    @Column(name = "MAX_TEMP")
    private BigDecimal maxTemperature;

    @Column(name = "HUMIDITY")
    private BigDecimal humidity;

     */

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Weather(String city) {
        this.city = city;

    }

    public WeatherListItemDTO toWeatherListItemDTO() {
        LocalDateTime timestamp = LocalDateTime.now();
        WeatherListMainItem mainItem = new WeatherListMainItem(null,null,null,null,null);

        return new WeatherListItemDTO(timestamp, mainItem, Collections.emptyList());
    }
}
