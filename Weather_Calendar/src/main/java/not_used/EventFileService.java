//package com.zogirdex.weather_calendar.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zogirdex.weather_calendar.manager.EventManager;
//import com.zogirdex.weather_calendar.model.ScheduledEvent;
//import com.zogirdex.weather_calendar.util.GlobalStateException;
//import com.zogirdex.weather_calendar.config.AppConstants;
//import java.io.File;
//import java.io.IOException;
//import java.util.Map;
//
//public class EventFileService {
//
//    private final EventManager eventManager;
//    private final ObjectMapper objectMapper;
//
//    public EventFileService() {
//        this.eventManager = EventManager.getInstance();
//        this.objectMapper = new ObjectMapper();  // Jackson ObjectMapper
//    }
//
//    // Zapisz stan spotkań do pliku JSON
//    public void saveEventsToFile(File file) throws GlobalStateException {
//        try {
//            // Uzyskanie stanu z EventManager (mapa dat => wydarzenie)
//            Map<String, ScheduledEvent> events = eventManager.getEvents();
//
//            // Zapisz do pliku
//            objectMapper.writeValue(file, events);
//            
//            // Możliwość zapisania ścieżki pliku jako ostatnio otwartego
//            this.saveLastOpenedFile(file);
//            
//        } catch (IOException e) {
//            throw new GlobalStateException("Błąd zapisywania stanu spotkań do pliku.", e);
//        }
//    }
//
//    // Ładowanie stanu spotkań z pliku JSON
//    public void loadEventsFromFile(File file) throws GlobalStateException {
//        try {
//            // Wczytanie mapy wydarzeń z pliku
//            Map<String, ScheduledEvent> events = objectMapper.readValue(file, Map.class);
//
//            // Zaktualizowanie EventManager o wczytane wydarzenia
//            eventManager.getEvents().clear();  // Czyszczenie obecnych wydarzeń
//            eventManager.getEvents().putAll(events);  // Wczytanie nowych
//
//            // Możliwość zapisania ścieżki pliku jako ostatnio otwartego
//            this.saveLastOpenedFile(file);
//
//        } catch (IOException e) {
//            throw new GlobalStateException("Błąd ładowania stanu spotkań z pliku.", e);
//        }
//    }
//
//    // Zapisz ścieżkę ostatnio otwartego pliku do konfiguracji
//    private void saveLastOpenedFile(File file) throws GlobalStateException {
//        try {
//            // Zapisz ścieżkę pliku jako ustawienie
//            AppConstants.setLastOpenedFile(file.getAbsolutePath());
//        } catch (Exception e) {
//            throw new GlobalStateException("Błąd zapisywania ostatnio otwartego pliku.", e);
//        }
//    }
//
//    // Wczytaj ostatnio otwarty plik
//    public File loadLastOpenedFile() {
//        String lastFilePath = AppConstants.getLastOpenedFile();
//        if (lastFilePath != null) {
//            return new File(lastFilePath);
//        } else {
//            return null;
//        }
//    }
//}
