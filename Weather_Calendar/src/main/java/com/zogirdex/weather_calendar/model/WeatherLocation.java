package com.zogirdex.weather_calendar.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WeatherLocation {
    private final Map<LocalDate, WeatherDay> weatherDays = new HashMap();

    public void addWeatherDay(WeatherDay weatherDay) {
        weatherDays.put(LocalDate.parse(weatherDay.getDatetime()), weatherDay);
    }

    public WeatherDay getWeatherDay(LocalDate date) {
        return weatherDays.get(date);  
    }
}

