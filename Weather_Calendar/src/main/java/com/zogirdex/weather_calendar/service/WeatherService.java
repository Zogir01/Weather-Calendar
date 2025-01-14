/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
    public WeatherService() throws WeatherApiException{
        try {
            this.weatherManager = WeatherManager.getInstance();
        }
        catch(WeatherApiException ex) {
            throw new WeatherApiException("Error while initalizing WeatherManager in WeatherService", ex);
        }
    }
    
    public WeatherDay getWeatherDay(CalendarItem item, String location)  {
        CalendarService.validateCalendarItem(item);
        LocalDate date = item.getDate();
        WeatherDay weatherDay = this.weatherManager.getWeatherDay(date, location);
        
        if(weatherDay == null) {
            // można by logować te informacje
            // np. logger.println("Brak danych pogody dla daty:" + localDate, tworze nowy obiekt.)
            // lub zamiast tworzenia nowego pustego WeatherDay, rzucić wyjątek?
            weatherDay = new WeatherDay(
                    date.toString(), 
                    0, 0, 0, 0, 0, 0, 0, 
                    "brak danych", "brak danych" ,"brak danych", "brak danych", "brak danych");
        }
        return weatherDay;
    }
}