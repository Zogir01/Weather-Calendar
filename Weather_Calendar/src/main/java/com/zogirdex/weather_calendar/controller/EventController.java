package com.zogirdex.weather_calendar.controller;

import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.service.EventService;
import com.zogirdex.weather_calendar.service.WeatherService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;

public class EventController implements Initializable{
    private EventService eventService;
    private WeatherService weatherService;
    private CalendarItem selectedItem;
    
    @FXML private TextField textFieldEventName;
    @FXML private TextArea textAreaEventDesc;
    @FXML private Label labelEventName;
    @FXML private Label labelEventDesc;
    @FXML private Button buttonSave;
    
    @Override
    public void initialize​(URL location, ResourceBundle resources) {
        this.eventService = new EventService();
        this.weatherService = new WeatherService();
        this.selectedItem = null;
    } 
    
    public void loadData(CalendarItem item) {
        this.selectedItem = item;
        
        try {
            WeatherDay weather = this.weatherService.getWeatherDay(this.selectedItem);
            System.out.println(weather.getDatetime());
            System.out.println("warunki: " + weather.getConditions());
            System.out.println("opis: " + weather.getDescription());
            System.out.println("ikona: " + weather.getIcon());
            
            ScheduledEvent event = this.eventService.getEvent(this.selectedItem);
            this.labelEventName.setText(event.getEventName());
            this.labelEventDesc.setText(event.getEventDesc());
        }
        catch(Exception ex) { // lub zlapac jakies ogólne wyjątki
            // SHOW ALERT
        }
    }
    
    @FXML
    private void saveData() {
        try {
            this.eventService.addEvent(selectedItem, this.textFieldEventName.getText(), this.textAreaEventDesc.getText());
         }
        catch(Exception ex) {
            // SHOW ALERT
        }
    }
}