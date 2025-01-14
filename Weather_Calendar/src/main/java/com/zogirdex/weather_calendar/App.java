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
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void init() {
        System.out.println("Inicjalizacja aplikacji");
    }
    
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