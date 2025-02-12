package com.zogirdex.weather_calendar.controller;

import com.zogirdex.weather_calendar.service.EventService;
import com.zogirdex.weather_calendar.service.WeatherService;
import com.zogirdex.weather_calendar.uiutil.AlertException;
import com.zogirdex.weather_calendar.uiutil.AlertSucces;
import com.zogirdex.weather_calendar.model.CalendarItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewEventController  implements Initializable {
    private EventService eventService;
    private WeatherService weatherService;
    private CalendarItem selectedItem;
    
    @FXML private TextField textFieldEventName;
    @FXML private TextArea textAreaEventDesc;
    @FXML private TextField textFieldEventLocation;
    @FXML private Button buttonAddEvent;
    
    @Override
    public void initialize​(URL location, ResourceBundle resources) {
        this.eventService = new EventService();
        this.weatherService = new WeatherService();
    } 
    
    public void init(CalendarItem item) {
        this.selectedItem = item;
    }
    
    @FXML
    private void saveData() {
        try {
            String location = this.textFieldEventLocation.getText();
            String eventName = this.textFieldEventName.getText();
            String eventDesc = this.textAreaEventDesc.getText();
            this.eventService.addEvent(selectedItem, eventName, eventDesc, location);
            this.weatherService.updateWeatherForLocation(location);
            this.weatherService.bindWeatherIconToCalendarItem(selectedItem, location);
            
            Stage currentStage = (Stage) buttonAddEvent.getScene().getWindow();
            currentStage.close();  
            new AlertSucces("Pomyślnie udało się zapisać nowe spotkanie.").showAndWait();   
         }
        catch(Exception ex) {
            new AlertException(ex).showAndWait();
        }
    }
}
