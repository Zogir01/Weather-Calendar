package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.util.GlobalStateAssistant;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDate;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.util.LinkedList;

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
    private final ObservableMap<LocalDate, ScheduledEvent> events;
   
    private EventManager() {
        ObservableMap<LocalDate, ScheduledEvent> state;
        try {
            state = GlobalStateAssistant.loadEventsState();
        }
        catch(ClassNotFoundException | IOException ex) {
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

    public void addEvent(LocalDate date, ScheduledEvent event) {
        events.put(date, event);
    }
    
    public ObservableMap<LocalDate, ScheduledEvent> getEvents() {
        return events;
    }
    
    public boolean eventExists(LocalDate date) {
        return this.events.containsKey(date);
    }
    
//    public final void saveToFile() {
//        FileAssistant.saveToFile(events);
//    }
//    
//    public final void loadFromFile() {
//        this.events = FileAssistant.loadFromFile();
//    }
}
