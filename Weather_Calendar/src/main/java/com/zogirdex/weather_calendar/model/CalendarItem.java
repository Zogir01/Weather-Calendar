package com.zogirdex.weather_calendar.model;

import com.zogirdex.weather_calendar.uiutil.CalendarButton;
import java.time.LocalDate;

public class CalendarItem {
    private final LocalDate date;
    private final int column;
    private final int row;
    private final CalendarButton dayButton;
    private final String initialText;
    
    public CalendarItem(LocalDate date, int column, int row, CalendarButton dayButton, String initialText) {
        this.date = date;
        this.column = column;
        this.row = row;
        this.dayButton = dayButton;
        this.initialText = initialText;
    }

    public int getColumn() { return this.column; }
    public int getRow() { return this.row; }
    public CalendarButton getButton() { return this.dayButton; }
    public LocalDate getDate() { return this.date; }
    public String getInitialText() { return this.initialText; }
    
    public void unbind() {
          this.dayButton.textProperty().unbind();
          this.dayButton.setText(String.valueOf(date.getDayOfMonth()));
          this.dayButton.unsetBackgroundImage();
    }
} 
