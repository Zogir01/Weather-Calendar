package com.zogirdex.weather_calendar.controller;

import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.uiutil.CalendarLabel;
import com.zogirdex.weather_calendar.uiutil.CalendarButton;
import com.zogirdex.weather_calendar.uiutil.StageAssistant;
import com.zogirdex.weather_calendar.service.CalendarService;
import com.zogirdex.weather_calendar.service.EventService;
import com.zogirdex.weather_calendar.util.ApiException;
import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.uiutil.AlertError;
import com.zogirdex.weather_calendar.uiutil.AlertException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import java.time.Year;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import java.io.IOException;

public class CalendarController implements Initializable {
    private CalendarService calendarService;

    @FXML private ComboBox<String> comboBoxMonths;
    @FXML private ComboBox<Year> comboBoxYears;
    @FXML private GridPane gridPaneCalendar;

    @Override
    public void initialize​(URL location, ResourceBundle resources) {
        this.calendarService = new CalendarService();
        this.fillComboBoxMonths();
        this.fillComboBoxYears();
        this.addDayLabels();
        this.loadCalendarData(); 
    } 
    
    @FXML
    public void testFunction(ActionEvent event) {
//        try {
//            // 2 OPCJE
//            //WindowManager.getInstance()..switchScene("secondary.fxml", this.getStage());
//            // tap ierwsza zakomentowana 
//            //WindowManager.getInstance().switchScene("secondary.fxml", event);
//            
//            // TESTOWO, ABY NIE LADOWAC ZBYT DUZO ZAPYTAN
//            WeatherService weatherService = new WeatherService();
//            weatherService.makeQuery("Gliwice");
//        }
//        catch(IOException ex) {
//            System.out.println("Blad podczas ladowania api...");
//            ex.printStackTrace();
//        }
    }
    
    @FXML
    public void loadCalendarData(){
        Year year = this.comboBoxYears.getSelectionModel().getSelectedItem();
        String month = this.comboBoxMonths.getSelectionModel().getSelectedItem();
        this.cleanCalendar();
        try {
            this.calendarService.generateCalendar(year, month, true, true).forEach(item -> {
                CalendarButton button = item.getButton();
                button.setOnAction(e -> calendarButton_click(item));
                this.gridPaneCalendar.add(button, item.getColumn(), item.getRow());
            });
        }
        catch(ApiException ex) {
            new AlertException(ex).showAndWait();
        }
    }
    
    @FXML
    private void calendarButton_click(CalendarItem item) {
        try {
            EventService eventService = new EventService();
            if(eventService.eventExists(item)) {
                this.openEventController(item);
             }
            else {
                this.openNewEventController(item);
            }
        }
       catch(Exception ex) {
           new AlertException(ex).showAndWait();
       }
    }
    
    private void openEventController(CalendarItem item) throws IOException {
       EventController controller = StageAssistant.getInstance().openNewStage(
            AppConstants.PATH_FXML_EVENT, 
            item.getDate().toString(), 
            true, 
            AppConstants.EVENT_STAGE_MIN_WIDTH, 
            AppConstants.EVENT_STAGE_MIN_HEIGHT
       );
       controller.loadCalendarItem(item);
    }
    
    private void openNewEventController(CalendarItem item) throws IOException {
        NewEventController controller = StageAssistant.getInstance().openNewStage(
            AppConstants.PATH_FXML_NEW_EVENT, 
            item.getDate().toString(), 
            true, 
            AppConstants.NEW_EVENT_STAGE_MIN_WIDTH, 
            AppConstants.NEW_EVENT_STAGE_MIN_HEIGHT
        );
        controller.loadCalendarItem(item);
    }
    
   private void fillComboBoxMonths() {
        this.comboBoxMonths.setItems(FXCollections.observableArrayList(this.calendarService.getAvailableMonthsTranslated()));
        if(!this.comboBoxMonths.getItems().isEmpty()){
            this.comboBoxMonths.getSelectionModel().select(this.calendarService.getCurrentMonthTranslated());
        }
        else {
            new AlertError("Wystąpił błąd z pobraniem dostepnych miesięcy w kalendarzu.").showAndWait();
        }
    }
    
    private void fillComboBoxYears() {
        this.comboBoxYears.setItems(FXCollections.observableArrayList(this.calendarService.getAvailableYears()));
        if(!this.comboBoxYears.getItems().isEmpty()){
            this.comboBoxYears.getSelectionModel().select(this.calendarService.getCurrentYear());
        }
        else {
            new AlertError("Wystąpił błąd z pobraniem dostepnych lat w kalendarzu.").showAndWait();
        }
     }
    
    private void addDayLabels() {
        this.gridPaneCalendar.add(new CalendarLabel("Poniedziałek"), 0, 0);
        this.gridPaneCalendar.add(new CalendarLabel("Wtorek"), 1, 0);
        this.gridPaneCalendar.add(new CalendarLabel("Środa"), 2, 0);
        this.gridPaneCalendar.add(new CalendarLabel("Czwartek"), 3, 0);
        this.gridPaneCalendar.add(new CalendarLabel("Piątek"), 4, 0);
        this.gridPaneCalendar.add(new CalendarLabel("Sobota"), 5, 0);
        this.gridPaneCalendar.add(new CalendarLabel("Niedziela"), 6, 0);
    }
    
     private void cleanCalendar() {
        this.gridPaneCalendar.getChildren().removeIf(node -> node instanceof CalendarButton);
    }
}
