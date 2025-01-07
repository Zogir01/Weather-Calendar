package com.zogirdex.weather_calendar;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;

public class SecondaryController implements Initializable{
    private EventManager eventManager;
    private CalendarItem selectedItem;
    
    @FXML private TextField textFieldEventName;
    @FXML private TextArea textAreaEventDesc;
    @FXML private Label labelEventName;
    @FXML private Label labelEventDesc;
    @FXML private Button buttonSave;
    
    @Override
    public void initialize​(URL location, ResourceBundle resources) {
        this.eventManager = EventManager.getInstance();
        this.selectedItem = null;
    } 
    
    public void loadData(CalendarItem item) {
        this.selectedItem = item;
        
        WeatherDay weather = EventManager.getInstance().getWeatherDay(this.selectedItem.getDate());
        if(weather != null) {
            System.out.println(weather.getDatetime());
            System.out.println("warunki: " + weather.getConditions());
            System.out.println("opis: " + weather.getDescription());
            System.out.println("ikona: " + weather.getIcon());
        }
        else {
            // SHOW ALERT
        }
    
        CalendarEvent event = this.eventManager.getEvent(this.selectedItem.getDate());
        
        if(event != null) {
            this.labelEventName.setText(event.getEventName());
            this.labelEventDesc.setText(event.getEventDesc());
        }
        else {
            this.labelEventName.setText("brak danych");
            this.labelEventDesc.setText("brak danych");
        }
    }
    
    @FXML
    private void saveData() {
        if(this.selectedItem != null) {
            CalendarEvent newEvent = new CalendarEvent(
                    this.textFieldEventName.getText(),
                    this.textAreaEventDesc.getText()
            );
            this.selectedItem.bindToEvent(newEvent, true);
            this.eventManager.addEvent(this.selectedItem.getDate(), newEvent);
            
            // Trochen iepotrzebnie robię nowy event i dodaje mu "binda", skoro event ten już istnieje.
            //this.mainController.loadCalendarData();
        }
        else {
            // SHOW WARNING
            // jakieś info, że należy najpierw załadować dane
        }
    }
}