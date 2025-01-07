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
    
    @FXML
    public void makeQuery() throws IOException {

        try {
            URL nbpEndpoint = new URL("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Gliwice?unitGroup=metric&elements=datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon&include=days%2Cfcst&key=VHEMMB29AXXDT86HR399VV4RT&contentType=json");
            
            HttpURLConnection nbpConnection = (HttpURLConnection) nbpEndpoint.openConnection();
            
            nbpConnection.setRequestProperty("Accept", "application/json");
            
            if (nbpConnection.getResponseCode() == 200) {
                
                BufferedReader br = new BufferedReader(new InputStreamReader(nbpConnection.getInputStream(), Charset.forName("UTF-8")));
                
                Gson gson = new Gson();
                
                //NBP API odsyła jednoelementową tablicę
                Query query = gson.fromJson(br, Query.class);
//              
                System.out.println("Query cost = " + query.getQueryCost());

                 query.getDays().forEach(elem -> {
                     System.out.println(elem.getDatetime());
                    System.out.println(elem.getDescription());
                    System.out.println(elem.getPressure());
                    System.out.println(elem.getPressure());
                 });
                 

//                Waluty waluty = walutyArray[0];
//                walutyCB.setItems(FXCollections.observableArrayList(waluty.getRates()));
//                
//                if(walutyCB.getItems().size()>0){
//                walutyCB.getSelectionModel().select(0);
//                }
                
                nbpConnection.disconnect();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
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
