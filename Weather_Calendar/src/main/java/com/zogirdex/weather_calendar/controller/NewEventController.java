package com.zogirdex.weather_calendar.controller;

import com.zogirdex.weather_calendar.service.EventService;
import com.zogirdex.weather_calendar.service.WeatherService;
import com.zogirdex.weather_calendar.uiutil.AlertError;
import com.zogirdex.weather_calendar.uiutil.AlertException;
import com.zogirdex.weather_calendar.uiutil.AlertSucces;
import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author tom3k
 */
public class NewEventController  implements Initializable {
    private EventService eventService;
    private WeatherService weatherService;
    private CalendarItem selectedItem;
    
    @FXML private TextField textFieldEventName;
    @FXML private TextArea textAreaEventDesc;
    @FXML private ComboBox<String> comboBoxLocation;
    @FXML private Button buttonAddEvent;
    
    @Override
    public void initialize​(URL location, ResourceBundle resources) {
        this.eventService = new EventService();
        this.weatherService = new WeatherService();
        this.fillComboBoxLocation();

    } 
    
    public void loadCalendarItem(CalendarItem item) {
        this.selectedItem = item;
    }
    
    @FXML
    private void saveData() {
        try {
            String location = this.comboBoxLocation.getSelectionModel().getSelectedItem();
            String eventName = this.textFieldEventName.getText();
            String eventDesc = this.textAreaEventDesc.getText();
            
            this.eventService.addEvent(selectedItem, eventName, eventDesc, location);
            this.weatherService.updateWeather(selectedItem, location);
            
            // Zamknięcie okna
            Stage currentStage = (Stage) buttonAddEvent.getScene().getWindow();
            currentStage.close();  
            
            new AlertSucces("Pomyślnie udało się zapisać nowe spotkanie.").showAndWait();   
         }
        catch(Exception ex) {
            new AlertException(ex).showAndWait();
        }
    }
    
    private void fillComboBoxLocation() {
        this.comboBoxLocation.setItems(FXCollections.observableArrayList(this.eventService.getAvailableLocations()));
        if(!this.comboBoxLocation.getItems().isEmpty()){
            this.comboBoxLocation.getSelectionModel().select(0);
        }
        else {
           new AlertError("Nie udało się pobrać lokalizacji.").showAndWait();
        }
    }
}
