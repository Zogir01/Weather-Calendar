/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zogirdex.weather_calendar.util;

import com.zogirdex.weather_calendar.model.ScheduledEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.ClassNotFoundException;
import java.util.HashMap;
import javafx.collections.ObservableMap;
import java.time.LocalDate;
import java.io.File;

/**
 *
 * @author tom3k
 */
public class GlobalStateAssistant {
    public GlobalStateAssistant() {}
    // pomyslec nad tym: getClass().getClassLoader().getResourceAsStream(EVENTS_STATE_FILE);
    public static final String EVENTS_STATE_FILE = "eventmanager.dat";
    public static final String FILES_STATE_FILE = "filemanager.dat";
    
    // wymagana jest zmiana z ObservableMap na HashMap, gdyż ObservableMap nie implementuje
    // interfejsu Serializable.
    public static final void saveEventsState(ObservableMap<LocalDate, ScheduledEvent> observableMap) throws IOException {
        HashMap<LocalDate, ScheduledEvent> map = new HashMap<>(observableMap);
        GlobalStateAssistant.saveState(map, EVENTS_STATE_FILE);
    }

    // wymagana jest zmiana z ObservableMap na HashMap, gdyż ObservableMap nie implementuje
    // interfejsu Serializable.
    public static final ObservableMap<LocalDate, ScheduledEvent> loadEventsState() throws IOException, ClassNotFoundException {
        HashMap<LocalDate, ScheduledEvent> loadedMap = GlobalStateAssistant.loadState(EVENTS_STATE_FILE);
        return javafx.collections.FXCollections.observableMap(loadedMap);
    }
    
    private static <T> T loadState(String path) throws IOException, ClassNotFoundException {
        // konstrukcja try-with-resources
        try (FileInputStream fileStream = new FileInputStream(path);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            return (T) objectStream.readObject();
        }
        catch(IOException ex) {
            throw new IOException("Binary file not found: " + path + ". Cannot load state.");
        }
        catch(ClassNotFoundException ex) {
            throw new ClassNotFoundException("This object cannot be loaded from this binary file: " + path);
        }
    }
    
    private static void saveState(Object obj, String path) throws IOException {
        // konstrukcja try-with-resources
        try (FileOutputStream fileStream = new FileOutputStream(path)) {
            ObjectOutputStream data = new ObjectOutputStream(fileStream);
            data.writeObject(obj);
        }
        catch(IOException  ex) {
            throw new IOException("Binary file not found: " + path + ". Cannot load state.");
        }
    }
}
