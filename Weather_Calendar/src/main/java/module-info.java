module com.zogirdex.weather_calendar {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.zogirdex.weather_calendar to javafx.fxml;
    exports com.zogirdex.weather_calendar;
}
