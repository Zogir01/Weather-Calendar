package com.zogirdex.weather_calendar.uiutil;

import javafx.scene.control.Alert;
/**
 *
 * @author tom3k
 */
public class AlertSucces extends Alert{
    public AlertSucces(String message) {
        super(AlertType.INFORMATION);
        setTitle("Informacja");
        setHeaderText(message);
    }
}
