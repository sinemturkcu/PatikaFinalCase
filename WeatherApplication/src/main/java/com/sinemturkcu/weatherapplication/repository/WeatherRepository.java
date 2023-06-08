package com.sinemturkcu.weatherapplication.repository;

import com.sinemturkcu.weatherapplication.entity.User;
import com.sinemturkcu.weatherapplication.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather,Long> {
    Weather findByCity(String city);
    Weather findByUserAndCity(User user, String city);


    @Query("SELECT w FROM Weather w WHERE w.user.id = :userId")
    List<Weather> findByUserId(@Param("userId") Long userId);

}
