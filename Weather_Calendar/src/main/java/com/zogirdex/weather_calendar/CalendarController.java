package com.zogirdex.weather_calendar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

//public class CalendarController extends ExtController implements Initializable {
public class CalendarController implements Initializable {
    private CalendarService calendarService;
    private final int YEARS_RANGE = 4;
    
    @FXML private ComboBox<Month> comboBoxMonths;
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
//            EventManager.getInstance().updateFromApi("Gliwice");
//        }
//        catch(IOException ex) {
//            
//        }
    }
    
    @FXML
    public void loadCalendarData(){
        Year year = this.comboBoxYears.getSelectionModel().getSelectedItem();
        Month month = this.comboBoxMonths.getSelectionModel().getSelectedItem();

        this.cleanCalendar();
       
        this.calendarService.generateCalendar(year, month, true, true).forEach(item -> {
            CalendarButton button = item.getButton();
            button.setOnAction(e -> dayButton_click(item));
            this.gridPaneCalendar.add(button, item.getColumn(), item.getRow());
        });
    }
    
    @FXML
    private void dayButton_click(CalendarItem item) {
        try {
         SecondaryController controller = WindowManager.getInstance().openNewWindow(
                 "secondary.fxml", item.getDate().toString(), true);
         controller.loadData(item);
        }
       catch(Exception ex) {
           System.out.print(ex.getMessage());
       }
    }
    
    private void fillComboBoxMonths() {
        this.comboBoxMonths.setItems(FXCollections.observableArrayList(Month.values()));
        if(!this.comboBoxMonths.getItems().isEmpty()){
            this.comboBoxMonths.getSelectionModel().select(LocalDate.now().getMonth());
        }
        else {
            // SHOW ALERT
        }
    }
    
    private void fillComboBoxYears() {
        ObservableList years = FXCollections.observableArrayList();
        int curYear = Year.now().getValue();
        for (int i = 0; i < this.YEARS_RANGE; i++) {
           years.add(Year.of(curYear + i));
        }
        this.comboBoxYears.setItems(years);
        if(!this.comboBoxYears.getItems().isEmpty()){
            this.comboBoxYears.getSelectionModel().select(Year.now());
        }
        else {
            // SHOW ALERT
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
