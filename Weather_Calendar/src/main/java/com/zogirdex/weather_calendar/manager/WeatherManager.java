package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.model.WeatherForecast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.time.Duration;

/**
 *
 * @author tom3k
 * 
 */
public class WeatherManager {
    private static WeatherManager instance;
    private final ObservableMap<String, WeatherForecast> weatherLocations;
    
    // nie wykorzystywane
    private final Map<String, LocalDateTime> lastUpdateTimes = new HashMap<>();
    
    
    private WeatherManager() {
        this.weatherLocations = FXCollections.observableHashMap();
    }
    
    public static synchronized WeatherManager getInstance() {
        if (instance == null) {
            instance = new WeatherManager();
        }
        return instance;
    }
    
    public WeatherForecast getWeatherForecast(String location) {
        return this.weatherLocations.getOrDefault(location, null);
    }
    
    public void addWeatherForecast(String location, WeatherForecast weatherLocation) {
        this.weatherLocations.put(location, weatherLocation);
    }
    
    public WeatherForecast getOrCreateWeatherForecast(String location) {
        return weatherLocations.computeIfAbsent(location, k -> new WeatherForecast());
//        WeatherForecast weatherLocation = this.getWeatherForecast(location);
//          if (weatherLocation == null) {
//              weatherLocation = new WeatherForecast();
//                this.addWeatherForecast(location, weatherLocation);
//          }
//          return weatherLocation;
      }
    
    // nie wykorzystywane
    public boolean shouldUpdate(String location, Duration minUpdateInterval) {
        LocalDateTime lastUpdate = lastUpdateTimes.get(location);
        return lastUpdate == null || Duration.between(lastUpdate, LocalDateTime.now()).compareTo(minUpdateInterval) > 0;
    }

    // nie wykorzystywane
    public void markUpdated(String location) {
        lastUpdateTimes.put(location, LocalDateTime.now());
    }
}
