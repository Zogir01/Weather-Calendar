package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.model.WeatherLocation;
import com.zogirdex.weather_calendar.model.WeatherQuery;
import com.zogirdex.weather_calendar.util.ApiException;
import com.zogirdex.weather_calendar.util.QueryAssistant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDate;
import java.util.Set;

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
    
//    public WeatherDay getWeatherDay(LocalDate date, String location) {
//        WeatherLocation weatherLocation = this.weatherLocations.getOrDefault(location, null);
//        return weatherLocation != null ? weatherLocation.getWeatherDay(date) : null;
//    }
    
//    public void addWeatherLocation(WeatherLocation weatherLocation) {
//        this.weatherLocations.put(weatherLocation.getLocation(), weatherLocation);
//    }
//    public void addWeatherDay(String location, WeatherDay weatherDay) {
//        WeatherLocation weatherLocation = this.weatherLocations.getOrDefault(location, null);
//        if(weatherLocation != null) {
//            weatherLocation.addWeatherDay(weatherDay);
//        }
//    }
    
//    // zaaktualizuje tylko jedną date, dla kolekcji lokalizacji
//   public final void updateWeather(Set<String> locations, LocalDate date) throws ApiException {
//        for(String location : locations) {
//            this.updateWeather(location, date);
//        }
//    }
//    
//   // zaaktualizuje 14 dat w przód, dla jednej lokalizacji (na tyle pozwala api)
//    public final void updateWeather(Set<String> locations) throws ApiException {
//        for(String location : locations) {
//            this.updateWeather(location);
//        }
//    }
//    
//    // zaaktualizuje tylko jedną date, dla jednej lokalizacji
//    public final void updateWeather(String location, LocalDate date) throws ApiException {
//        this.printDebug(String.format("Performing query to weather API: location = %s, date = %s", location, date.toString()));
//        WeatherQuery result = this.makeQuery(location);
//        this.getOrCreateWeatherLocation(location).addWeatherDay(result.getDay(date));
//    }
//    
//    // zaaktualizuje 14 dat w przód dla jednej lokalizacji (na tyle pozwala api)
//   public final void updateWeather(String location) throws ApiException {
//        this.printDebug(String.format("Performing query to weather API: location = %s", location));
//        WeatherQuery result = this.makeQuery(location);
//        WeatherLocation weatherLocation = this.getOrCreateWeatherLocation(location);
//        result.getDays().forEach(weatherLocation::addWeatherDay);
//    }
//   
//    private void printDebug(String message) {
//        if(AppConstants.DEBUG_MODE) {
//            System.out.println(message);
//         }
//    }
   
//   private WeatherQuery makeQuery(String location) throws ApiException {
//        String url = QueryAssistant.buildUrl(AppConstants.WEATHER_API_BASE_URL + location, 
//               AppConstants.WEATHER_API_QUERY_PARAMS);
//       
//        return QueryAssistant.makeQuery(url, WeatherQuery.class);
//   }
}
