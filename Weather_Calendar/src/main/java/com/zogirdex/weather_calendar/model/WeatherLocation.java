package com.zogirdex.weather_calendar.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WeatherLocation {
    private String location;
    private final Map<LocalDate, WeatherDay> weatherDays;

    public WeatherLocation(String location) {
        this.location = location;
        this.weatherDays = new HashMap<>();
    }

    public void addWeatherDay(WeatherDay weatherDay) {
        weatherDays.put(LocalDate.parse(weatherDay.getDatetime()), weatherDay);
    }

    public WeatherDay getWeatherDay(LocalDate date) {
        return weatherDays.get(date);  
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }     
}

