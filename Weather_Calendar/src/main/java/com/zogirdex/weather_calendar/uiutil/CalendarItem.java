package com.zogirdex.weather_calendar.uiutil;

import java.time.LocalDate;

/**
 *
 * @author tom3k
 */
public class CalendarItem {
    private LocalDate date;
    private int column;
    private int row;
    private CalendarButton dayButton;
    private String initialText;
    
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
} 
