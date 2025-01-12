package com.zogirdex.weather_calendar;

import com.zogirdex.weather_calendar.uiutil.StageManager;
import com.zogirdex.weather_calendar.util.GlobalStateAssistant;
import com.zogirdex.weather_calendar.manager.EventManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import java.io.File;
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
        StageManager.getInstance().openNewStage("/com/zogirdex/weather_calendar/calendar.fxml", "Kalendarz", true);

    }
    
    @Override
    public void stop() {
        try {
            //GlobalStateAssistant.saveStates();
            GlobalStateAssistant.saveEventsState(EventManager.getInstance().getEvents());
            //testowo
            //FileManager.getInstance().saveToNewFile(EventManager.getInstance().getEvents(), new File("events.json"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
            //logger: pojawił się problem podczas zapisu stanu globalnych obiektów.
        }
        
        
        System.out.println("Aplikacja zakonczona.");
    }

    public static void main(String[] args) {
        launch();
    }

}