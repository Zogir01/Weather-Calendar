package com.zogirdex.weather_calendar;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 *
 * @author tom3k
 */
public class WeatherManager {
    private static WeatherManager instance;
    private final ObservableMap<LocalDate, WeatherDay> weatherDays = FXCollections.observableHashMap();
   
    private WeatherManager() {
    }
    
    // getInstance wzorca singleton (synchronized, aby ułatwić wielowątkowość, którą można by zaimplementować)
    public static synchronized WeatherManager getInstance() {
        if (instance == null) {
            instance = new WeatherManager();
        }
        return instance;
    }
    
    public WeatherDay getWeatherDay(LocalDate date) {
        return this.weatherDays.getOrDefault(date, null);
    }
    
    public void addWeatherDay(LocalDate date, WeatherDay weatherDay) {
        this.weatherDays.put(date, weatherDay);
    }
}
