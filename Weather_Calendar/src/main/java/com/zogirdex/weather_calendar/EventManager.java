package com.zogirdex.weather_calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDate;

/**
 *
 * @author tom3k
 * 
 * Może lepsze byłoby rozdzielenie na "EventManager" oraz "WeatherManager", tymbardziej
 * jak dojdzie więcej funkcji (np. odczyt/zapis do pliku).
 * 
 * EventManager przechowuje globalny stan aplikacji
 * 
 */
public class EventManager {
    private static EventManager instance;
    private final ObservableMap<LocalDate, ScheduledEvent> events = FXCollections.observableHashMap();
   
    private EventManager() {
        // Przykładowe dane
        events.put(LocalDate.now(), new ScheduledEvent("Spotkanie", "Opis spotkania", "5"));
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
}
