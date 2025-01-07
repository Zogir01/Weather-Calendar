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
    
//    public void addEvent(LocalDate date, String eventName, String eventDesc) {
//        if(eventName != "" && )
//        
//        CalendarEvent event = new CalendarEvent(eventName, eventDesc);
//        events.put(date, event);
//    }
    
    public ObservableMap<LocalDate, CalendarEvent> getEvents() {
        return events;
    }
    
    public WeatherDay getWeatherDay(LocalDate date) {
        return this.weatherDays.getOrDefault(date, null);
    }
    
    private void addWeatherDay(LocalDate date, WeatherDay weatherDay) {
        this.weatherDays.put(date, weatherDay);
    }
    
    public void updateFromApi(String location) throws IOException {
        Query query = WeatherApiService.getInstance().makeQuery(location);
         query.getDays().forEach(elem -> {
         this.addWeatherDay(LocalDate.parse(elem.getDatetime()), elem);
         //format elem.getDatetime(): 2025-01-07
         });
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
