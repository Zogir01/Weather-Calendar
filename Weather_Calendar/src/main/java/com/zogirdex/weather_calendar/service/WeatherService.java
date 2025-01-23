package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.manager.WeatherManager;
import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.util.WeatherApiException;
import java.time.LocalDate;

/**
 *
 * @author tom3k
 */
public class WeatherService {
    private final WeatherManager weatherManager;
    
    public WeatherService() throws WeatherApiException {
        try {
            this.weatherManager = WeatherManager.getInstance();
        }
        catch(WeatherApiException ex) {
            throw new WeatherApiException("Wystąpił błąd komunikacji z api pogodowym.", ex);
        }
    }
    
    public WeatherDay getWeatherDay(CalendarItem item, String location)  {
        this.validateCalendarItem(item);
        LocalDate date = item.getDate();
        WeatherDay weatherDay = this.weatherManager.getWeatherDay(date, location);
        return weatherDay != null ? weatherDay : new WeatherDay(
                    date.toString(), 
                    0, 0, 0, 0, 0, 0, 0, 
                    "brak danych", "brak danych" ,"brak danych", "brak danych", "brak danych");
    }
    
    public void bindWeatherIconToCalendarItem(CalendarItem item, String location) {
        WeatherDay weatherDay = getWeatherDay(item, location);
        if (weatherDay != null) {
            item.getButton().setBackgroundImage("img/weather-icon-trsp/" + weatherDay.getIcon() + ".png");
            weatherDay.iconProperty().addListener((observable, oldVal, newVal) -> {
                item.getButton().setBackgroundImage("img/weather-icon-trsp/"  + newVal + ".png");
            });
        }
    }
    
    private void validateCalendarItem(CalendarItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Przekazano pusty CalendarItem.");
        }
    }
}