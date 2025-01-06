package com.zogirdex.weather_calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDate;

/**
 *
 * @author tom3k
 */
public class EventManager {
    private static EventManager instance;
    private final ObservableMap<LocalDate, CalendarEvent> events = FXCollections.observableHashMap();

    private EventManager() {
        // Przykładowe dane
        events.put(LocalDate.now(), new CalendarEvent("Spotkanie", "Opis spotkania", "Słonecznie, 20 stopni"));
    }
    
    // getInstance wzorca singleton (synchronized, aby ułatwić wielowątkowość, którą można by zaimplementować)
    public static synchronized EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public CalendarEvent getEvent(LocalDate date) {
        return events.getOrDefault(date, null);
    }

    public void addEvent(LocalDate date, CalendarEvent event) {
        events.put(date, event);
    }

    public ObservableMap<LocalDate, CalendarEvent> getEvents() {
        return events;
    }
    
    // ładowanie danych z api zrobić wielowątkowo (chat miał jakiś pomysł fajny)
//    public void loadWeatherData () {
//        for (Entry<Object, Object> entry : FXCollections.observableHashMap().entrySet()) {
//            LocalDate key = (LocalDate)entry.getKey();
//            CalendarEvent value = (CalendarEvent)entry.getValue();
//
//            System.out.println("Key: " + key + ", Value: " + value);
//        }
//    }
}
