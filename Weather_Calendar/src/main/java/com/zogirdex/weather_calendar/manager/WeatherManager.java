package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.model.WeatherLocation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDate;

/**
 *
 * @author tom3k
 * Nowa wersja, aby możliwe było pobierania pogody dla różnych lokalizacji dodano Pair<LocalDate, String>
 * -edit: usunięto tą parę i dodano nową klasę WeatherLocation
 */
public class WeatherManager {
    private static WeatherManager instance;
    private final ObservableMap<String, WeatherLocation> weatherLocations = FXCollections.observableHashMap();
    
    private WeatherManager() {
    }
    
    // getInstance wzorca singleton (synchronized, aby ułatwić wielowątkowość, którą można by zaimplementować)
    public static synchronized WeatherManager getInstance() {
        if (instance == null) {
            instance = new WeatherManager();
        }
        return instance;
    }
    
    public WeatherLocation getWeatherLocation(String location) {
        return this.weatherLocations.getOrDefault(location, null);
    }
    
    public void addWeatherLocation(WeatherLocation weatherLocation) {
        this.weatherLocations.put(weatherLocation.getLocation(), weatherLocation);
    }

    public WeatherDay getWeatherDay(LocalDate date, String location) {
        WeatherLocation weatherLocation = this.weatherLocations.getOrDefault(location, null);
        if(weatherLocation == null) {
            return null;
        }  
        return weatherLocation.getWeatherDay(date);
    }

    public void addWeatherDay(String location, WeatherDay weatherDay) {
        WeatherLocation weatherLocation = this.weatherLocations.getOrDefault(location, null);
        if(weatherLocation != null) {
            weatherLocation.addWeatherDay(weatherDay);
        }
    }
}
