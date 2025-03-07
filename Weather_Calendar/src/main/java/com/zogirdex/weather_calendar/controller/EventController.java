package com.zogirdex.weather_calendar.controller;

import com.zogirdex.weather_calendar.model.CalendarItem;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.service.EventService;
import com.zogirdex.weather_calendar.service.WeatherService;
import com.zogirdex.weather_calendar.uiutil.AlertException;
import com.zogirdex.weather_calendar.uiutil.AlertSucces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class EventController implements Initializable{
    private EventService eventService;
    private WeatherService weatherService;
    private CalendarItem selectedItem;
    
    @FXML private TextField textFieldEditEventName;
    @FXML private TextArea textAreaEditEventDesc;
    @FXML private TextField textFieldEditEventLocation;
    
    @FXML private Label labelEventName;
    @FXML private Label labelEventDesc;
    @FXML private Label labelLocation;
    
    @FXML private Button buttonEditEvent;
    @FXML private Button buttonDeleteEvent;
    @FXML private Label labelDatetime;
    @FXML private Label labelTemperature; 
    @FXML private Label labelHumidity; 
    @FXML private Label labelPrecip; 
    @FXML private Label labelPrecipprob; 
    @FXML private Label labelSnow; 
    @FXML private Label labelPressure; 
    @FXML private Label labelCloudcover; 
    @FXML private Label labelSunrise; 
    @FXML private Label labelSunset; 
    @FXML private Label labelConditions; 
    @FXML private Label labelDescription; 
    @FXML private ImageView imageView;
    
    @Override
    public void initialize​(URL location, ResourceBundle resources) {
        this.eventService = new EventService();
        this.weatherService = new WeatherService();
    } 
    
    public void init(CalendarItem item) {
        this.selectedItem = item;
        try {
            this.setControls();
        }
        catch(Exception ex) { 
            new AlertException(ex).showAndWait();
        }
    }
    @FXML
    private void deleteEvent() {
        try {
            this.eventService.deleteEvent(this.selectedItem);
            
            // Zamknięcie okna
            Stage currentStage = (Stage) buttonDeleteEvent.getScene().getWindow();
            currentStage.close();  
            
            this.selectedItem.unbind();
           
            new AlertSucces("Pomyślnie udało się usunąć spotkanie.").showAndWait();   
        }
        catch(Exception ex) {
            new AlertException(ex).showAndWait();
        }
    }
    
    @FXML
    private void editEvent() {
        try {
            String location = this.textFieldEditEventLocation.getText();
            String eventName = this.textFieldEditEventName.getText();
            String eventDesc = this.textAreaEditEventDesc.getText();
            
            // Edycja spotkania
            this.eventService.editEvent(this.selectedItem, eventName, eventDesc, location);
            
            // Aktualizacja pogody
            this.weatherService.updateWeatherForLocation(location);

            // Powiązanie pogody z elementem kalendarza
            this.weatherService.bindWeatherIconToCalendarItem(selectedItem, location);
            
            this.setControls();
            
            new AlertSucces("Pomyślnie udało się edytować spotkanie.").showAndWait();   
         }
        catch(Exception ex) {
            new AlertException(ex).showAndWait();
        }
    }
    
    private void setControls() {
            ScheduledEvent event = this.eventService.getEvent(this.selectedItem);
            
            this.labelEventName.textProperty().bind(event.eventNameProperty());
            this.labelEventDesc.textProperty().bind(event.eventDescProperty());
            this.labelLocation.textProperty().bind(event.locationProperty());
            this.textFieldEditEventName.setText(event.getEventName());
            this.textAreaEditEventDesc.setText(event.getEventDesc());
            this.textFieldEditEventLocation.setText(event.getLocation());
            
            WeatherDay weather = this.weatherService.getWeatherDay(this.selectedItem, event.getLocation());
            
            this.labelDatetime.textProperty().bind(weather.datetimeProperty());
            this.labelTemperature.textProperty().bind(weather.tempProperty().asString());
            this.labelHumidity.textProperty().bind(weather.humidityProperty().asString());
            this.labelPrecip.textProperty().bind(weather.precipProperty().asString());
            this.labelPrecipprob.textProperty().bind(weather.precipprobProperty().asString());
            this.labelSnow.textProperty().bind(weather.snowProperty().asString());
            this.labelPressure.textProperty().bind(weather.pressureProperty().asString());
            this.labelCloudcover.textProperty().bind(weather.cloudcoverProperty().asString());
            this.labelSunrise.textProperty().bind(weather.sunriseProperty());
            this.labelSunset.textProperty().bind(weather.sunsetProperty());
            this.labelConditions.textProperty().bind(weather.conditionsProperty());
            this.labelDescription.textProperty().bind(weather.descriptionProperty());
            this.imageView.setImage(new Image("img/weather-icon-trsp/" + weather.getIcon() + ".png"));
    }
}


