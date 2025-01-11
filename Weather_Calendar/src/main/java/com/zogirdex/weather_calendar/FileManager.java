package com.zogirdex.weather_calendar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableMapValue;

import java.time.LocalDate;
import java.util.function.Consumer;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.*;
import java.lang.IllegalStateException;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tom3k
 * ew. rozdzielić także na FileService
 */
public class FileManager {
    private final LinkedList<File> fileList;
    private final Gson gson;
    private static FileManager instance;
    
    public static FileManager getInstance() throws IOException {
        if(instance == null) {
            instance = new FileManager();
            //this.instance = (FileManager)GlobalStateManager.loadState("")
        }
        return instance;
    }
    
    private FileManager() throws IOException {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(StringProperty.class, new StringPropertyAdapter())
                .create();
        LinkedList<File> files = new LinkedList();
        
        try {
            files = GlobalStateAssistant.loadState(GlobalStateAssistant.FILES_STATE_FILE, files.getClass());
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        this.fileList = files;

        
//          this.gson = new GsonBuilder().setPrettyPrinting().create();
//          
//            File stateFile = new File("file_state.json");
//
//            if (stateFile.exists()) {
//                this.fileList = this.readFromFile(stateFile, new TypeToken<LinkedList<File>>() {});
//            }
//            else {
//                this.fileList = new LinkedList<File>();
//            }
    }
    
    // Saves to file without saving it into list. 
    private <T> void saveToFile(T object, File file) throws IOException {
        FileWriter fileWriter  = new FileWriter(file);
        String jsonStr = this.gson.toJson(object);
        fileWriter.write(jsonStr);
        fileWriter.close();
    }
    
    public final <T> void saveToNewFile(T object, File file) throws IOException {
        this.saveToFile(object, file);
        this.fileList.add(file);
    }
    
    public final <T> void saveToLastFile(T object) throws IOException {
        if (this.fileList.isEmpty()) {
            throw new IllegalStateException("File list is empty. Cannot save object to file. "
                    + "Run saveToNewFile method to create new file.");
        }
        
        File lastFile = this.fileList.getLast();
        this.saveToFile(object, lastFile);
    }
   
    public final <T> T readFromFile(File file, Class<T> classOfT) throws IOException {
         FileReader fileReader = new FileReader(file);
         return gson.fromJson(fileReader, classOfT);
    }
    
    // For deserializing a generic collection. (popatrz że gson.fromJson też ma podobne metody)
    public final <T> T readFromFile(File file, TypeToken typeOfT) throws IOException {
        FileReader reader = new FileReader(file);
        T object = this.gson.fromJson(reader, typeOfT.getType());
        reader.close();
        return object;
    }

    public final <T> T readFromLastFile(Class<T> classOfT) throws IOException {
        if (fileList.isEmpty()) {
            throw new IllegalStateException("File list is empty. Cannot load object from file. "
                    + "Run saveToNewFile method to create new file.");
        }
        File lastFile = this.fileList.getLast();
        return readFromFile(lastFile, classOfT);
    }
  
    
//    private void loadState() throws IOException {
////        if (!stateFile.exists()) {
////            return; // Jeśli plik nie istnieje, zaczynamy z pustą listą
////        }
//    List<File> file = new LinkedList<File>();
//            file = this.readFromFile(new File("test.json"), file.getClass());
//
//        FileReader fileReader = new FileReader("file_manager_state.json") ;
//        Type listType = new TypeToken<LinkedList<File>>() {}.getType();
//        LinkedList<File> loadedList = gson.fromJson(fileReader, listType);
//        if (loadedList != null) {
//            fileList.addAll(loadedList);
//        }
//        
//    }

    public List<File> getFileList() {
        return this.fileList;
    }
    
    public File getLastFile() {
        if(!this.fileList.isEmpty()) {
            return this.fileList.getLast();
        }
        else {
            return null;
        }
    }
    
}
