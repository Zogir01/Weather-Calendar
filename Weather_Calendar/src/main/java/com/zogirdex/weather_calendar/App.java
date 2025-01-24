package com.zogirdex.weather_calendar;

import com.zogirdex.weather_calendar.uiutil.StageAssistant;
import com.zogirdex.weather_calendar.util.GlobalStateAssistant;
import com.zogirdex.weather_calendar.util.GlobalStateException;
import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.manager.WeatherManager;
import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.model.WeatherLocation;
import com.zogirdex.weather_calendar.model.WeatherQuery;
import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.util.ApiException;
import com.zogirdex.weather_calendar.util.QueryAssistant;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Set;
import java.time.LocalDate;

/**
 * Główna klasa aplikacji JavaFX. Otwierane jest tutaj pierwsze okno oraz zapisywany jest stan modelu (danych) do
 * pliku binarnego.
 */
public class App extends Application {
      /**
     * Metoda inicjalizacyjna aplikacji, wykonująca się po stworzeniu instancji klasy Application.
     */
    @Override
    public void init() {
        System.out.println("Inicjalizacja aplikacji");
        EventManager eventManager = EventManager.getInstance();
        WeatherManager weatherManager = WeatherManager.getInstance();
        try {
            // zaladuj najpierw stan EventManager
            eventManager.loadEventsState();
            Set<String> locations = eventManager.getLocations();
            if(!locations.isEmpty()) {
                for (String location : locations) {
                    String url = QueryAssistant.buildUrl(AppConstants.WEATHER_API_BASE_URL + location, 
                            AppConstants.WEATHER_API_QUERY_PARAMS);
                     WeatherQuery result = QueryAssistant.makeQuery(url, WeatherQuery.class);
                     WeatherLocation weatherLocation = weatherManager.getOrCreateWeatherLocation(location);
                     for(WeatherDay day : result.getDays()) {
                         weatherLocation.addWeatherDay(day);
                     }
                    // result.getDays().forEach(weatherLocation::addWeatherDay);
                 }
            }
        }
        catch(GlobalStateException | ApiException ex) {
            // LOGUJ
        }
    }
    
     /**
     * Metoda wykonująca się po metodzie init, w parametrze otrzymujemy obiekt Stage, czyli obiekt
     * otwartego okna. W metodzie tej otwierane jest okno za pomocą klasy singleton StageManager.
     * @param stage Obiekt nowo otwartego okna.
     * @throws IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        StageAssistant.getInstance().openNewStage(
                AppConstants.PATH_FXML_CALENDAR, 
                AppConstants.CALENDAR_STAGE_NAME, 
                true, 
                AppConstants.CALENDAR_STAGE_MIN_WIDTH, 
                AppConstants.CALENDAR_STAGE_MIN_HEIGHT
        );
    }
    
     /**
     * Metoda wykonująca się po zamknięciu aplikacji, czyli gdy ostatnie otwarte okno zostanie zamknięte lub
     * wywołano metodę Platform.exit(). W stop zapisywany jest stan danych aplikacji do pliku binarnego, 
     * za pomocą klasy singleton EventManager.
     */
    @Override
    public void stop() {
        try {
            EventManager.getInstance().saveEventsState();
        }
        catch (GlobalStateException ex) {
            // LOGUJ
        }
        System.out.println("Aplikacja zakonczona.");
    }

    public static void main(String[] args) {
        launch();
    }
}