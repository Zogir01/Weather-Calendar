package com.zogirdex.weather_calendar.uiutil;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class CalendarButton extends Button {
    private final String originalStyle;  // Przechowujemy oryginalny styl
    
    public CalendarButton(String text, String tooltipText) {
        super(text);
        this.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.getStyleClass().add("callendar-button");
        this.getStyleClass().add("callendar-button-hover");

        Tooltip tp = new Tooltip();
        tp.setText(tooltipText);
        tp.setShowDelay(Duration.millis(500)); 
        tp.setShowDuration(Duration.seconds(5)); 
        tp.setHideDelay(Duration.millis(200));
        this.setTooltip(tp);

        this.originalStyle = this.getStyle();
    }

    public void setBackgroundImage(String url) {
        this.setStyle(this.originalStyle + String.format("-fx-background-image: url('%s');", url));
    }

    public void unsetBackgroundImage() {
        this.setStyle(this.originalStyle);
    }
}