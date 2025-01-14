package com.zogirdex.weather_calendar.controller;

import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.service.EventService;
import com.zogirdex.weather_calendar.service.WeatherService;
import com.zogirdex.weather_calendar.util.WeatherApiException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;

public class EventController implements Initializable{
    private EventService eventService;
    private WeatherService weatherService;
    private CalendarItem selectedItem;
    
    @FXML private TextField textFieldEventName;
    @FXML private TextArea textAreaEventDesc;
    @FXML private ComboBox<String> comboBoxLocation;
    @FXML private Label labelEventName;
    @FXML private Label labelEventDesc;
    @FXML private Label labelLocation;
    @FXML private Button buttonSave;
    
    @Override
    public void initialize​(URL location, ResourceBundle resources) {
        try {
            this.eventService = new EventService();
            this.weatherService = new WeatherService();
        }
        catch(WeatherApiException ex) {
            // ALERT
        }

        this.selectedItem = null;
        this.fillComboBoxLocation();
    } 
    
    private void fillComboBoxLocation() {
        List<String> locations = new ArrayList<String>() ;
        Collections.addAll(locations, "Gliwice", "Katowice", "Zabrze", "Paniówki");
        
        this.comboBoxLocation.setItems(FXCollections.observableArrayList(locations));
        if(!this.comboBoxLocation.getItems().isEmpty()){
            this.comboBoxLocation.getSelectionModel().select(0);
        }
        else {
            // SHOW ALERT
        }
    }
    
    public void loadData(CalendarItem item) {
        this.selectedItem = item;
        
        try {
            ScheduledEvent event = this.eventService.getEvent(this.selectedItem);
            String eventName = event.getEventName();
            String eventDesc = event.getEventDesc();
            String location = event.getLocation();
            
            this.labelEventName.setText(eventName);
            this.labelEventDesc.setText(eventDesc);
            this.labelLocation.setText(location);
            
            this.textFieldEventName.setText(eventName);
            this.textAreaEventDesc.setText(eventDesc);
            this.comboBoxLocation.getSelectionModel().select(location);
        
            WeatherDay weather = this.weatherService.getWeatherDay(this.selectedItem, location);
            System.out.println(weather.getDatetime());
            System.out.println("warunki: " + weather.getConditions());
            System.out.println("opis: " + weather.getDescription());
            System.out.println("ikona: " + weather.getIcon());
        }
        catch(Exception ex) { // lub zlapac jakies ogólne wyjątki
            ex.printStackTrace();
            // SHOW ALERT
        }
    }
    
    @FXML
    private void saveData() {
        try {
            String location = this.comboBoxLocation.getSelectionModel().getSelectedItem();
            
            this.eventService.addEvent(selectedItem, this.textFieldEventName.getText(), this.textAreaEventDesc.getText(), 
                    location);
         }
        catch(Exception ex) {
            ex.printStackTrace();
            // SHOW ALERT
        }
    }
}