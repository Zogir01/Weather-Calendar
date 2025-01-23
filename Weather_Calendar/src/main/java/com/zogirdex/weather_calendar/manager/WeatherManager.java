package com.zogirdex.weather_calendar.manager;

import com.google.gson.Gson;
import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.model.WeatherLocation;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.model.WeatherQuery;
import com.zogirdex.weather_calendar.util.WeatherApiException;
import com.zogirdex.weather_calendar.util.GlobalStateException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author tom3k
 * Nowa wersja, aby możliwe było pobierania pogody dla różnych lokalizacji dodano Pair<LocalDate, String>
 * -edit: usunięto tą parę i dodano nową klasę WeatherLocation
 */
public class WeatherManager {
    private static WeatherManager instance;
    private final ObservableMap<String, WeatherLocation> weatherLocations = FXCollections.observableHashMap();
    private String apiQueryParamString;
    
    
    private WeatherManager() throws WeatherApiException {
        // tworzenie stringa z parametrami zapytania do api (parametry znajdują się w AppConstants)
        StringBuilder builder = new StringBuilder().append("?");    
        for (Map.Entry<String, String> entry : AppConstants.WEATHER_API_QUERY_PARAMS.entrySet()) {
            builder.append(entry.getKey())
                           .append("=")
                           .append(entry.getValue())
                           .append("&");
        }
        this.apiQueryParamString = builder.toString();
        
        // inicjalizuje swój model na podstawie wczytanych eventów.
        if(AppConstants.WEATHER_API_AUTO_QUERY) {
                EventManager eventManager;
                eventManager = EventManager.getInstance();

                
                // tworzę Set aby lokalizacje były unikalne - aby nie tworzyć wielu zapytań niepotrzebnie do tej samej lokalizacji.
                Set <String> uniqueLocations = new HashSet();
                for(ScheduledEvent event : eventManager.getEvents().values()) {
                    uniqueLocations.add(event.getLocation());
                }
                
                for(String uniqueLocation : uniqueLocations) {
                    try {
                        this.makeQuery(uniqueLocation);
                    }
                    catch(WeatherApiException ex) {
                        throw new WeatherApiException("Error performing weather api query while creating instance of "
                                + "WeatherManager.", ex);
                    }
                }
          }
    }
    
    // getInstance wzorca singleton (synchronized, aby ułatwić wielowątkowość, którą można by zaimplementować)
    public static synchronized WeatherManager getInstance() throws WeatherApiException{
        if (instance == null) {
            instance = new WeatherManager();
        }
        return instance;
    }
    
    public WeatherLocation getWeatherLocation(String location) {
        return this.weatherLocations.getOrDefault(location, null);
    }
    
    public void addWeatherLocation(WeatherLocation weatherLocation) {
        this.weatherLocations.put(weatherLocation.getLocation(), weatherLocation);
    }
    
    public boolean weatherLocationExists(String location) {
        return this.weatherLocations.containsKey(location);
    }

    public WeatherDay getWeatherDay(LocalDate date, String location) {
        WeatherLocation weatherLocation = this.weatherLocations.getOrDefault(location, null);
        if(weatherLocation == null) {
            return null;
        }  
        return weatherLocation.getWeatherDay(date);
    }

    public void addWeatherDay(String location, WeatherDay weatherDay) {
        WeatherLocation weatherLocation = this.weatherLocations.getOrDefault(location, null);
        if(weatherLocation != null) {
            weatherLocation.addWeatherDay(weatherDay);
        }
    }
    
    // zaaktualizuje tylko jedną date, dla jednej lokalizacji
    public final void makeQuery(String location, LocalDate date) throws WeatherApiException {
       if(AppConstants.DEBUG_MODE) {
           System.out.println(String.format("Performing query to weather API: location = %s, date = %s", location, date.toString()));
       }
        makeQuery(location, date, true);
    }
    
    // zaaktualizuje 14 dat w przód dla jednej lokalizacji
   public final void makeQuery(String location) throws WeatherApiException {
       if(AppConstants.DEBUG_MODE) {
           System.out.println(String.format("Performing query to weather API: location = %s", location));
       }
        makeQuery(location, null, false);
    }
    
    private void makeQuery(String location, LocalDate date, boolean readOneDate) throws WeatherApiException {
        String finalUrl = AppConstants.WEATHER_API_BASE_URL + location + this.apiQueryParamString;
        //WeatherManager weatherManager = WeatherManager.getInstance();

        try {
            URL endpoint = new URL(finalUrl);
            HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            
            if (conn.getResponseCode() == 200) {  
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), 
                        Charset.forName("UTF-8"))
                );
                
                Gson gson = new Gson();
                WeatherQuery query = gson.fromJson(br, WeatherQuery.class);
                
                WeatherLocation weatherLocation = this.getWeatherLocation(location);
                
                // mozna by to lepiej zoptymalizowac - tzn. jeśli WeatherManager nie ma takiego WeatherLocation (=null), to
                // tworzymy ten obiekt i dodajemy mu WeatherDay, a dopiero na samym końcu wstawiamy ten obiekt do 
                // WeatherManager.
                if (weatherLocation == null) {
                    this.addWeatherLocation(new WeatherLocation(location));
                }
                if(!readOneDate) { // dodaje 14 nowych WeatherDay
                    for(WeatherDay day : query.getDays()) {
                        this.addWeatherDay(location, day);
                    }
                }
                else { // dodaje jeden WeatherDay do określonej daty
                    for(WeatherDay day : query.getDays()) {
                        LocalDate dateFromQuery = LocalDate.parse(day.getDatetime());
                        if(dateFromQuery.equals(date)) {
                            this.addWeatherDay(location, day);
                        }
                    }  
                }
                conn.disconnect();
            }
            else {
                // można by obsłużyć jeszcze więcej http status codes.
                throw new WeatherApiException("Error while creating weather API query, get response status code = " 
                        + conn.getResponseCode());
            }
        } catch (IOException ex) {
            throw new WeatherApiException("Error while creating weather API query.", ex);
        }
    }
    
//    private String createParamString () {
//        StringBuilder builder = new StringBuilder();    
//        builder.append("?");
//        for (Map.Entry<String, String> entry : AppConstants.QUERY_PARAMS.entrySet()) {
//            builder.append(entry.getKey())
//                           .append("=")
//                           .append(entry.getValue())
//                           .append("&");
//            }
//        return builder.toString();
//    }
}
