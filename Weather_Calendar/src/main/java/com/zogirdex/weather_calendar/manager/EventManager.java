package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.util.GlobalStateAssistant;
import com.zogirdex.weather_calendar.util.GlobalStateException;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import not_used.WeatherApiAssistant;
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

    public void addEvent(LocalDate date, ScheduledEvent event) throws WeatherApiException{
        if(events.put(date, event) != null) {
            if(AppConstants.WEATHER_API_AUTO_QUERY) {
                try {
                    WeatherManager.getInstance().makeQuery(event.getLocation());
                }
                catch(WeatherApiException ex) {
                        throw new WeatherApiException("When new event was added, error occured "
                                + "while performing weather api query.", ex);
                }
            }
        }
        else {
            throw new IllegalArgumentException("Cannot add event with the assigned date: " + date.toString());
            // events.put zwraca null lub zwraca wyjątki jeśli nie mógł dodać do mapy
            // mozna zrobic własną klase wyjątku
        }
    }
    
    public ObservableMap<LocalDate, ScheduledEvent> getEvents() {
        return events;
    }
    
    public boolean eventExists(LocalDate date) {
        return this.events.containsKey(date);
    }
}
