package com.zogirdex.weather_calendar.uiutil;

import javafx.scene.control.Label;

public class CalendarLabel extends Label {

    public CalendarLabel(String dayName) {
        super(dayName);
        initializeStyle();
    }

    private void initializeStyle() {
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.getStyleClass().add("calendar-label");
    }
}
