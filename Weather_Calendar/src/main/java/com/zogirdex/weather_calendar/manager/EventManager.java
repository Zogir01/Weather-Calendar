package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.util.GlobalStateAssistant;
import com.zogirdex.weather_calendar.util.GlobalStateException;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.util.WeatherApiAssistant;
import com.zogirdex.weather_calendar.util.WeatherApiException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDate;

/**
 *
 * @author tom3k
 * 
 * EventManager przechowuje globalny stan aplikacji
 * 
 */
public class EventManager {
    private static EventManager instance;
    private final ObservableMap<LocalDate, ScheduledEvent> events;
   
    private EventManager() {
        ObservableMap<LocalDate, ScheduledEvent> state;
        try {
            state = GlobalStateAssistant.loadEventsState();
        }
        catch(GlobalStateException ex) {
            state = FXCollections.observableHashMap();
            // LOGGER?
        }
        this.events = state;
        
        if(AppConstants.WEATHER_API_AUTO_QUERY && !this.events.isEmpty()) {
            for(ScheduledEvent event : this.events.values()) {
                try {
                    WeatherApiAssistant.makeQuery(event.getLocation());
                }
                catch(WeatherApiException ex) {
                    //throw new WeatherApiException("Error while performing initial query to load weather data.", ex);
                    // tutaj można by coś rzucić
                 }
            }
        }
    }
    
    // getInstance wzorca singleton (synchronized, aby ułatwić wielowątkowość, którą można by zaimplementować)
    public static synchronized EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public ScheduledEvent getEvent(LocalDate date) {
        return events.getOrDefault(date, null);
    }

    public void addEvent(LocalDate date, ScheduledEvent event) {
        events.put(date, event);
    }
    
    public ObservableMap<LocalDate, ScheduledEvent> getEvents() {
        return events;
    }
    
    public boolean eventExists(LocalDate date) {
        return this.events.containsKey(date);
    }
    
     public void makeWeatherQuery (LocalDate date) throws WeatherApiException, IllegalArgumentException{
           try {
               ScheduledEvent event = this.events.get(date);
               if(event == null) {
                   throw new IllegalArgumentException("Cannot find any event with the assigned date: " + date.toString());
               }
                // aktualizacja danych pogodowych w przypadku znalezienia eventu.
                WeatherApiAssistant.makeQuery(event.getLocation());
           }
           catch(WeatherApiException ex) {
               throw new WeatherApiException("Error while performing query.", ex);
           }
       }
}
