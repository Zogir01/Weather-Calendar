package com.zogirdex.weather_calendar.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WeatherForecast {
    private final Map<LocalDate, WeatherDay> weatherDays = new HashMap();
    
    public void addWeatherDay(WeatherDay weatherDay) {
        weatherDays.put(LocalDate.parse(weatherDay.getDatetime()), weatherDay);
    }
    
    public void addOrUpdateWeatherDay(WeatherDay weatherDay) {
        LocalDate date = LocalDate.parse(weatherDay.getDatetime()); 
        WeatherDay existingDay = this.weatherDays.get(date);
    
        // robione w ten sposób, gdyż metoda addWeatherDay stworzy na nowo obiekt, co oznacza
        // usunięcie przypisanych "Observables".
        if(existingDay != null) {
            existingDay.setCloudcover(weatherDay.getCloudcover());
            existingDay.setConditions(weatherDay.getConditions());
            existingDay.setDatetime(weatherDay.getDatetime());
            existingDay.setDescription(weatherDay.getDescription());
            existingDay.setHumidity(weatherDay.getHumidity());
            existingDay.setIcon(weatherDay.getIcon());
            existingDay.setPrecip(weatherDay.getPrecip());
            existingDay.setPrecipprob(weatherDay.getPrecipprob());
            existingDay.setPressure(weatherDay.getPressure());
            existingDay.setSnow(weatherDay.getSnow());
            existingDay.setSunrise(weatherDay.getSunrise());
            existingDay.setSunset(weatherDay.getSunset());
            existingDay.setTemp(weatherDay.getTemp());
        }
        else {
            weatherDays.put(date, weatherDay); 
        }
        
    }

    public WeatherDay getWeatherDay(LocalDate date) {
        return weatherDays.get(date);  
    }
}

