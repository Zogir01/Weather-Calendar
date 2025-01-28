/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zogirdex.weather_calendar.uiutil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author tom3k
 */
public class AlertError extends Alert {
    public AlertError(String message) {
        super(AlertType.ERROR);
        setTitle("Błąd aplikacji");
        setHeaderText(message);
    }    
}
