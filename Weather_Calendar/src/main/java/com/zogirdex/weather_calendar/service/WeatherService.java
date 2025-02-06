package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.manager.WeatherManager;
import com.zogirdex.weather_calendar.model.CalendarItem;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.model.WeatherForecast;
import com.zogirdex.weather_calendar.model.WeatherQuery;
import com.zogirdex.weather_calendar.util.ApiException;
import com.zogirdex.weather_calendar.util.QueryAssistant;
import java.time.LocalDate;
import java.lang.IllegalArgumentException;
import java.util.Set;
import java.util.Map;
import java.util.List;

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
        
        WeatherForecast weatherLocation = this.weatherManager.getWeatherForecast(location);
        if(weatherLocation == null) {
            throw new IllegalArgumentException("Nie udało się odnaleźć pogody dla określonej lokalizacji: " + location);
        }
        
        WeatherDay weatherDay = weatherLocation.getWeatherDay(date);
        if(weatherDay == null) {
            throw new IllegalArgumentException("Nie udało się odnaleźć pogody dla określonego dnia: " + date.toString());
        }
        return weatherDay;
    }
    
    public void updateWeatherForLocation(String location) throws ApiException {
           WeatherQuery result;
            try {
                result = this.makeQuery(location);
            }
            catch(ApiException ex) {
              throw new ApiException("Wystąpił błąd podczas aktualizowania danych pogodowych dla lokalizacji: " + location, ex);
            }
            
            WeatherForecast weatherLocation = this.weatherManager.getOrCreateWeatherForecast(location);
            
            for(WeatherDay weatherDay : result.getDays()) {
                weatherLocation.addOrUpdateWeatherDay(weatherDay);

            }
    }
    
    public void bindWeatherIconToCalendarItem(CalendarItem item, String location) {
        this.validateCalendarItem(item);
        
        WeatherDay weatherDay = this.getWeatherDay(item, location);
        
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
        String url = QueryAssistant.buildUrl(this.apiBaseUrl, location, this.apiQueryParams);
        return QueryAssistant.makeQuery(url, WeatherQuery.class);
    }
}