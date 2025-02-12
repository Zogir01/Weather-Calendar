package com.zogirdex.weather_calendar;

import com.zogirdex.weather_calendar.uiutil.StageAssistant;
import com.zogirdex.weather_calendar.util.GlobalStateException;
import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.service.AutoWeatherService;
import com.zogirdex.weather_calendar.config.AppConstants;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Główna klasa aplikacji JavaFX. W klasie tej zaimplementowano inicjalizacje oraz zapis stanu aplikacji.
 */
public class AppService extends Application {
    private AutoWeatherService autoWeatherService;
      /**
     * Metoda inicjalizacyjna aplikacji, wykonująca się po stworzeniu instancji klasy Application. Ładowany jest stan
     * klas singleton EventManager oraz WeatherManager. Do stanu w WeatherManager jest zapisywane 14 dat
     * z pogodą dla każdej unikalnej lokalizacji z klasy EventManager. Ważne jest aby najpierw EventManager odczytał
     * swój stan, a w przypadku gdy takowego nie ma (brak lokalizacji), nie wykonywać wtedy odczytu z API.
     */
    @Override
    public void init() {
        System.out.println("Inicjalizacja aplikacji");
        EventManager eventManager = EventManager.getInstance();
        try {
            eventManager.loadEventsState();
            this.autoWeatherService = new AutoWeatherService();
        }
        catch(GlobalStateException ex) {}
    }
    
     /**
     * Metoda wykonująca się po metodzie init. W metodzie tej wykorzystano klasę StageAssistant do otwarcia pierwszego,
     * głównego okna aplikacji. Parametrami przekazanymi do openNewStage są stałe zmienne, określone w pliku
     * AppConstants.
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
     * wywołano metodę Platform.exit(). W metodzie tej zapisywany jest stan danych aplikacji do pliku binarnego, 
     * za pomocą klasy singleton EventManager.
     */
    @Override
    public void stop() {
        try {
            EventManager.getInstance().saveEventsState();
            autoWeatherService.shutdown();
        }
        catch (GlobalStateException ex) {

        }
        System.out.println("Aplikacja zakonczona.");
    }

    public static void main(String[] args) {
        launch();
    }
}