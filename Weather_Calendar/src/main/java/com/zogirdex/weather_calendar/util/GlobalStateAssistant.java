package com.zogirdex.weather_calendar.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GlobalStateAssistant {
    public GlobalStateAssistant() {}
    // pomyslec nad tym: getClass().getClassLoader().getResourceAsStream(EVENTS_STATE_FILE);
    
    public static <T> T loadState(String path) throws GlobalStateException {
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
    
    public static void saveState(Object obj, String path) throws GlobalStateException {
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
