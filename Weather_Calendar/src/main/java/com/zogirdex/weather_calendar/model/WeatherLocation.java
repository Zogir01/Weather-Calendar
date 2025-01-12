package com.zogirdex.weather_calendar.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WeatherLocation {
    private String location;
    private final Map<LocalDate, WeatherDay> weatherDaysMap;

    public WeatherLocation(String location) {
        this.location = location;
        this.weatherDaysMap = new HashMap<>();
    }

    public void addWeatherDay(WeatherDay weatherDay) {
        weatherDaysMap.put(LocalDate.parse(weatherDay.getDatetime()), weatherDay);
    }

    public WeatherDay getWeatherDay(LocalDate date) {
        return weatherDaysMap.get(date);  
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }     
}

