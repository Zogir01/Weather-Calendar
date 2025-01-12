module com.zogirdex.weather_calendar {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports com.zogirdex.weather_calendar;
    
    opens com.zogirdex.weather_calendar to javafx.fxml, com.google.gson;
    opens com.zogirdex.weather_calendar.controller to javafx.fxml, com.google.gson;

}
  
