/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zogirdex.weather_calendar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.ClassNotFoundException;
import java.io.File;

/**
 *
 * @author tom3k
 */
public class GlobalStateAssistant {
    public GlobalStateAssistant() {

    }
    public static final String EVENTS_STATE_FILE = "eventmanager.dat";
    public static final String FILES_STATE_FILE = "filemanager.dat";
    
    public static <T> T loadState(String path, Class<T> classOfT) throws IOException, ClassNotFoundException {
//        File file = new File(path);
//        if (!file.exists()) {
//            throw new FileNotFoundException("Binary file not found: " + path);
//        }

        FileInputStream fileStream = new FileInputStream(path);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        T obj = (T) objectStream.readObject();
        objectStream.close();
        fileStream.close();

        return obj;
    }
    
    private static final void saveState(Object obj, String path) throws IOException {
         FileOutputStream file = new FileOutputStream(path);
         ObjectOutputStream data = new ObjectOutputStream(file);
         data.writeObject(obj);
         data.close();
         file.close();
    }
    
    public static void saveStates() throws IOException {
        //GlobalStateAssistant.saveState(EventManager.getInstance().getEvents(), EVENTS_STATE_FILE);
        GlobalStateAssistant.saveState(FileManager.getInstance().getFileList(), FILES_STATE_FILE);
    }
    
//    public static void loadStates() {
//        GlobalStateManager.saveState(FileManager.getInstance().getFileList() = (FileManager)GlobalStateManager.loadState(FILES_STATE_FILE);
//    }
}
