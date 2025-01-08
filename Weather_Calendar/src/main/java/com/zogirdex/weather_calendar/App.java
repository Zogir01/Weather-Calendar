package com.zogirdex.weather_calendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        StageManager.getInstance().openNewStage("calendar.fxml", "Kalendarz", true);
    }
    
    @Override
    public void stop() {
        System.out.println("Aplikacja zakonczona.");
    }

    public static void main(String[] args) {
        launch();
    }

}