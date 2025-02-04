package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.model.WeatherLocation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 *
 * @author tom3k
 * 
 */
public class WeatherManager {
    private static WeatherManager instance;
    private final ObservableMap<String, WeatherLocation> weatherLocations;
    
    private WeatherManager() {
        this.weatherLocations = FXCollections.observableHashMap();
    }
    
    public static synchronized WeatherManager getInstance() {
        if (instance == null) {
            instance = new WeatherManager();
        }
        return instance;
    }
    
    public WeatherLocation getWeatherLocation(String location) {
        return this.weatherLocations.getOrDefault(location, null);
    }
    
    public void addWeatherLocation(String location, WeatherLocation weatherLocation) {
        this.weatherLocations.put(location, weatherLocation);
    }
    
    public WeatherLocation getOrCreateWeatherLocation(String location) {
        WeatherLocation weatherLocation = this.getWeatherLocation(location);
          if (weatherLocation == null) {
              weatherLocation = new WeatherLocation();
                this.addWeatherLocation(location, weatherLocation);
          }
          return weatherLocation;
      }
}
