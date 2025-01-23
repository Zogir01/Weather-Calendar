package com.zogirdex.weather_calendar;

import com.zogirdex.weather_calendar.uiutil.StageManager;
import com.zogirdex.weather_calendar.util.GlobalStateAssistant;
import com.zogirdex.weather_calendar.util.GlobalStateException;
import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.config.AppConstants;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

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
    }
    
     /**
     * Metoda wykonująca się po metodzie init, w parametrze otrzymujemy obiekt Stage, czyli obiekt
     * otwartego okna. W metodzie tej otwierane jest okno za pomocą klasy singleton StageManager.
     * @param stage Obiekt nowo otwartego okna.
     * @throws IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        StageManager.getInstance().openNewStage(
                AppConstants.CALENDAR_FXML_PATH, 
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
            ex.printStackTrace();
            //logger: pojawił się problem podczas zapisu stanu globalnych obiektów?
        }
        System.out.println("Aplikacja zakonczona.");
    }

    public static void main(String[] args) {
        launch();
    }
}