package com.zogirdex.weather_calendar.util;

import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.config.AppConstants;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import javafx.collections.ObservableMap;
import java.time.LocalDate;

/**
 *
 * @author tom3k
 * 
 */
public class GlobalStateAssistant {
    public GlobalStateAssistant() {}
    // pomyslec nad tym: getClass().getClassLoader().getResourceAsStream(EVENTS_STATE_FILE);
    
    // wymagana jest zmiana z ObservableMap na HashMap, gdyż ObservableMap nie implementuje
    // interfejsu Serializable.
    public static final void saveEventsState(ObservableMap<LocalDate, ScheduledEvent> observableMap) throws GlobalStateException {
        HashMap<LocalDate, ScheduledEvent> map = new HashMap<>(observableMap);
        GlobalStateAssistant.saveState(map, AppConstants.EVENTS_STATE_PATH);
    }

    // wymagana jest zmiana z ObservableMap na HashMap, gdyż ObservableMap nie implementuje
    // interfejsu Serializable.
    public static final ObservableMap<LocalDate, ScheduledEvent> loadEventsState() throws GlobalStateException {
        HashMap<LocalDate, ScheduledEvent> loadedMap = GlobalStateAssistant.loadState(AppConstants.EVENTS_STATE_PATH);
        return javafx.collections.FXCollections.observableMap(loadedMap);
    }
    
    private static <T> T loadState(String path) throws GlobalStateException {
        // konstrukcja try-with-resources
        try (FileInputStream fileStream = new FileInputStream(path);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            return (T) objectStream.readObject();
        }
        catch(IOException ex) {
            throw new GlobalStateException("Binary file not found: " + path + ". Cannot load state.", ex);
        }
        catch(ClassNotFoundException ex) {
            throw new GlobalStateException("This object cannot be loaded from this binary file: " + path, ex);
        }
    }
    
    private static void saveState(Object obj, String path) throws GlobalStateException {
        // konstrukcja try-with-resources
        try (FileOutputStream fileStream = new FileOutputStream(path)) {
            ObjectOutputStream data = new ObjectOutputStream(fileStream);
            data.writeObject(obj);
        }
        catch(IOException  ex) {
            throw new GlobalStateException("Binary file not found: " + path + ". Cannot load state.", ex);
        }
    }
}
