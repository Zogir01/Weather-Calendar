package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.manager.WeatherManager;
import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.model.WeatherLocation;
import com.zogirdex.weather_calendar.model.WeatherQuery;
import com.zogirdex.weather_calendar.util.ApiException;
import com.zogirdex.weather_calendar.util.QueryAssistant;
import java.time.LocalDate;
import java.lang.IllegalArgumentException;
import java.util.Set;
import java.util.Map;

/**
 *
 * @author tom3k
 */
public class WeatherService {
    
    private final WeatherManager weatherManager;
    
    private final String apiBaseUrl;
    private final Map<String, String> apiQueryParams;
    
    public WeatherService() {
         this.weatherManager = WeatherManager.getInstance();
         this.apiBaseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
         this.apiQueryParams = Map.of(
            "unitGroup", "metric",
            "elements", "datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon",
            "include", "days%2Cfcst",
            "key", "VHEMMB29AXXDT86HR399VV4RT",
            "contentType", "json"
        );
    }
    
    public WeatherDay getWeatherDay(CalendarItem item, String location)  {
        this.validateCalendarItem(item);
        LocalDate date = item.getDate();
        WeatherDay weatherDay = this.weatherManager.getWeatherLocation(location).getWeatherDay(date);
        if(weatherDay == null) {
            throw new IllegalArgumentException("Brak pogody dla określonego spotkania.");
        }
        return weatherDay;
    }
    
    public void updateWeather(CalendarItem item, String location) throws ApiException{
        this.validateCalendarItem(item);
         try {
            WeatherQuery result = this.makeQuery(location);
            WeatherLocation weatherLocation = this.weatherManager.getOrCreateWeatherLocation(location);
            
            for(WeatherDay weatherDay : result.getDays()) {
                LocalDate date = item.getDate();
                LocalDate queryDate = LocalDate.parse(weatherDay.getDatetime());
                if(date.equals(queryDate)) {
                    // aktualizacja modelu tylko dla określonego dnia
                    weatherLocation.addOrUpdateWeatherDay(weatherDay);
                    this.bindWeatherIconToCalendarItem(item, weatherDay);
                }
            }
          } 
          catch(ApiException ex) {
              throw new ApiException("Wystąpił błąd podczas aktualizowania danych pogodowych.", ex);
          }
    }
    
    public void updateWeather(Set<String> locations) throws ApiException{
        if(locations.isEmpty()) {
            throw new IllegalArgumentException("Nie można zaaktualizować pogody dla nieistniejących lokalizacji.");
        }
        
        try {
            for (String location : locations) {
                WeatherQuery result = this.makeQuery(location);
                 WeatherLocation weatherLocation = weatherManager.getOrCreateWeatherLocation(location);
                 for(WeatherDay day : result.getDays()) {
                     weatherLocation.addWeatherDay(day);
                 }
             }
        }
        catch(ApiException ex) {
              throw new ApiException("Wystąpił błąd podczas aktualizowania danych pogodowych.", ex);
        }
    }
    
    public void bindWeatherIconToCalendarItem(CalendarItem item, WeatherDay weatherDay) throws ApiException{
        this.setCalendarItemBgImage(item, weatherDay.getIcon());
        weatherDay.iconProperty().addListener((observable, oldVal, newVal) -> {
            this.setCalendarItemBgImage(item, newVal);
            System.out.print(newVal);
        });
    }
    private void setCalendarItemBgImage(CalendarItem item, String iconName) {
        item.getButton().setBackgroundImage("img/weather-icon-trsp/" + iconName + ".png");
    }
    
    private void validateCalendarItem(CalendarItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Przekazano pusty CalendarItem.");
        }
    }
    
    private WeatherQuery makeQuery(String location) throws ApiException {
        String url = QueryAssistant.buildUrl(this.apiBaseUrl + location, this.apiQueryParams);
        return QueryAssistant.makeQuery(url, WeatherQuery.class);
    }
}