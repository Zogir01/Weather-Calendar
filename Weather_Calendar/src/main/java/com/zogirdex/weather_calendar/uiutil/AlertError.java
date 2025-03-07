package com.zogirdex.weather_calendar.uiutil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertError extends Alert {
    public AlertError(String message) {
        super(AlertType.ERROR);
        setTitle("Błąd aplikacji");
        setHeaderText(message);
    }    
}
