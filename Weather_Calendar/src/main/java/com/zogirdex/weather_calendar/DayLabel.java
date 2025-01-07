package com.zogirdex.weather_calendar;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class DayLabel extends Label {

    public DayLabel(String dayName) {
        super(dayName);
        initializeStyle();
    }

    private void initializeStyle() {
        this.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.setTextFill(Color.DARKBLUE);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setStyle("-fx-alignment: center; " 
                + "-fx-font-size: 14px;" 
                + "-fx-background-color: #dfe3ee; " 
                + "-fx-border-color: #a9a9a9; "   
                + "-fx-border-width: 1px; " 
                + "-fx-padding: 5px;");
    }
}
