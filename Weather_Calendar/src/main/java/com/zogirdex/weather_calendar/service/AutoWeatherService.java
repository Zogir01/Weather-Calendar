package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.util.ApiException;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.collections.MapChangeListener;
import java.lang.IllegalArgumentException;

public class AutoWeatherService {
    private final EventManager eventManager;
    private final ScheduledExecutorService scheduler;
    private final WeatherService weatherService;

    public AutoWeatherService() {
        this.eventManager = EventManager.getInstance();
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.weatherService = new WeatherService();

        // Wstępne zaaktualizowanie pogody
        this.updateWeatherForAllEventLocations();
        
        // Rozpoczęcie automatycznej aktualizacji pogody co 30 minut
        this.startScheduledWeatherUpdate();
    }

    private void startScheduledWeatherUpdate() {
        scheduler.scheduleAtFixedRate(() -> {
                this.updateWeatherForAllEventLocations();
        }, 0, 30, TimeUnit.MINUTES);
    }

    private void updateWeatherForAllEventLocations() {
        System.out.println("Aktualizuje pogodę dla wszystkich lokalizacji.");
        
        Set<String> locations = eventManager.getLocations();
        
        if(locations.isEmpty()) {
            // dodać inny mechanizm logowania
            System.out.println("Brak dostępnych lokalizacji do aktualizacji pogody.");
            return;
        }
        for (String location : locations) {
            this.updateWeatherForLocation(location);
        }
    }
    
    private void updateWeatherForLocation(String location) {
        try {
             weatherService.updateWeatherForLocation(location);
         }
         catch(ApiException ex) {
             System.err.println("Błąd podczas aktualizacji pogody: " + ex.getMessage());
         }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
