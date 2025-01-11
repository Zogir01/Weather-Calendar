package com.zogirdex.weather_calendar;

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
        
        String test1 = "jakis obiekt do zapisania 1";
        String test2 = "jakis obiekt do zapisania 2";
        
        try {
            File file1 = new File("plik1.json");
            File file2 = new File("plik2.json");
            FileManager.getInstance().saveToNewFile(test1, file1);
            FileManager.getInstance().saveToNewFile(test2, file2);
            System.out.println(file1.getAbsolutePath());
            System.out.println(file2.getAbsolutePath());
            
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        StageManager.getInstance().openNewStage("calendar.fxml", "Kalendarz", true);

    }
    
    @Override
    public void stop() {
        try {
            GlobalStateAssistant.saveStates();
            
            //testowo
            FileManager.getInstance().saveToNewFile(EventManager.getInstance().getEvents(), new File("events.json"));
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