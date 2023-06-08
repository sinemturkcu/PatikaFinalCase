package com.sinemturkcu.weatherapplication.entity;

import com.sinemturkcu.weatherapplication.dto.WeatherListItemDTO;
import com.sinemturkcu.weatherapplication.dto.WeatherListMainItem;
import com.sinemturkcu.weatherapplication.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;

@Entity
@Table(name = "WEATHER")
@NoArgsConstructor
@Getter
@Setter
public class Weather {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="WEATHER_ID_SEQ"
    )
    private Long id;

    @Column(name = "CITY")
    private String city;

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
