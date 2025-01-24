package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.util.GlobalStateAssistant;
import com.zogirdex.weather_calendar.util.GlobalStateException;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.util.ApiException;
import javafx.collections.ObservableMap;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

/**
 * EventManager przechowuje globalny stan aplikacji odnośnie utworzonych spotkań. Jest to klasa singleton, a więc
 * dostęp do tej klasy jest możliwy z każdej innej klasy aplikacji. Możliwe jest stworzenie tylko pojedyńczej instancji tej klasy
 * (singleton), a więc model danych (kolekcja z eventami) także może mieć tylko jedną instancję.
 * 
 * (W projekcie założono, że z klas typu "Manager" mogą korzystać tylko klasy typu "Service" lub klasa "App".)
 */
public class EventManager {
    private static EventManager instance;
    private final ObservableMap<LocalDate, ScheduledEvent> events;
   
    private EventManager() {
        HashMap<LocalDate, ScheduledEvent> state;
        try {
             state = GlobalStateAssistant.loadState(AppConstants.PATH_EVENTS_STATE);
        }
        catch(GlobalStateException ex) {
            state = new HashMap();    
        }
        this.events = javafx.collections.FXCollections.observableMap(state);
    }
    public static synchronized EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public ScheduledEvent getEvent(LocalDate date) {
        return events.getOrDefault(date, null);
    }
    
    public ObservableMap<LocalDate, ScheduledEvent> getEvents() {
        return this.events;
     }
    
    // Zwraca unikalną kolekcje lokalizacji zapisanych w mapie events.
    public Set<String> getLocations() {
        Set <String> locations = new HashSet();
        for(ScheduledEvent event : this.events.values()) {
            locations.add(event.getLocation());
        }
        return locations;
     }

    public void addEvent(LocalDate date, ScheduledEvent event) throws ApiException{
        events.put(date, event);
        if(AppConstants.WEATHER_API_AUTO_QUERY) {
            try {
                WeatherManager.getInstance().updateWeather(event.getLocation());
            }
            catch(ApiException ex) {
                    throw new ApiException("When new event was added, error occured "
                            + "while performing weather api query.", ex);
            }
        }
    }
    
    // wymagana jest zmiana z ObservableMap na HashMap, gdyż ObservableMap nie implementuje
    // interfejsu Serializable. Przydałoby się aby ten kod wykonywała jakaś inna klasa.
    public final void saveEventsState() throws GlobalStateException {
        GlobalStateAssistant.saveState(new HashMap<>(this.events), AppConstants.PATH_EVENTS_STATE);
    }
}
