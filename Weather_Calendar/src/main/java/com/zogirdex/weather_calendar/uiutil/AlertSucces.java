package com.zogirdex.weather_calendar.uiutil;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
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
