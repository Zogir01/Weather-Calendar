package com.zogirdex.weather_calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDate;
import java.lang.NullPointerException;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.google.gson.Gson;

import java.util.Map;
import java.util.HashMap;
import javafx.util.Pair;

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
    private final ObservableMap<LocalDate, CalendarEvent> events = FXCollections.observableHashMap();
    private final ObservableMap<LocalDate, WeatherDay> weatherDays = FXCollections.observableHashMap();
    //private final ObservableMap<Pair<LocalDate, CalendarEvent>> events = FXCollections.observableHashMap();
   
    
    private EventManager() {
        // Przykładowe dane
        events.put(LocalDate.now(), new CalendarEvent("Spotkanie", "Opis spotkania"));
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
    
    public boolean eventExists(LocalDate date) {
        return this.events.containsKey(date);
    }
    
    public WeatherDay getWeatherDay(LocalDate date) {
        return this.weatherDays.getOrDefault(date, null);
    }
    
    public void addWeatherDay(LocalDate date, WeatherDay weatherDay) {
        this.weatherDays.put(date, weatherDay);
    }
    
    public static enum bindType {
        BY_EVENT_NAME,
        BY_EVENT_DESC
    }
    
    public void bindCalendarItemToEvent(CalendarItem item) {
         if(item == null) {
             throw new IllegalArgumentException("Given calendarItem item was null.");
         }
         
         LocalDate date = item.getDate();
         
         if(date == null) {
             throw new IllegalArgumentException("Given calendarItem field 'date' was null.");
         }
         
        CalendarEvent event = this.getEvent(date);
        
         if (event == null) {
             throw new IllegalArgumentException(String.format("Event with given LocalDate = %s was null.", date.toString()));
         }
         
         DayButton button = item.getButton();
         
         if(button == null) {
             throw new IllegalArgumentException("Given calendarItem field 'button' was null");
         }
         
         String initialText = item.getInitialText();
         
         if(initialText == null) {
             throw new IllegalArgumentException("Given calendarItem field 'initialText' was null.");
         }
         
          button.setText(initialText + event.getEventName());
          event.eventNameProperty().addListener((observable, oldVal, newVal) -> {
                button.setText(initialText + newVal);
                //mozna by zmieniac jeszcze np. wyglad buttona
          });
    }
}
