package com.zogirdex.weather_calendar;

import java.time.LocalDate;

/**
 *
 * @author tom3k
 */
public class CalendarItem {
    private LocalDate date;
    private int column;
    private int row;
    private DayButton button;
    private String initialText;
    
    public CalendarItem(LocalDate date, int column, int row, boolean showDayValue, boolean bindToEvent) {
        this.date = date;
        this.column = column;
        this.row = row;
        
        if(showDayValue) {
            this.initialText = String.valueOf(this.date.getDayOfMonth()).concat("\n");
            this.button = new DayButton(String.valueOf(this.date.getDayOfMonth()), date.toString());
        }
        else {
            this.initialText = "";
            this.button = new DayButton("", date.toString());
        }
        
        if(bindToEvent) {
            CalendarEvent event = EventManager.getInstance().getEvent(date);
            
            if (event != null) {
                this.bindToEvent(event, true);
            }
        }
    }
    
    public final void bindToEvent(CalendarEvent event, boolean showDayValue) {
        this.button.setText(this.initialText + event.getEventName());
        event.eventNameProperty().addListener((observable, oldVal, newVal) -> {
            this.button.setText(this.initialText + newVal);
            // można by jeszcze np. zmieniać wygląd buttona
        });
    }
    
    public int getColumn() { return this.column; }
    public int getRow() { return this.row; }
    public DayButton getButton() { return this.button; }
    public LocalDate getDate() { return this.date; }
    
    // Oj ciężko xd
    //    public final <T> void bindToValue(ObservableValue<T> value, boolean showDayValue) {
    //        this.button.setText(initialText + value.getValue());
    //        value.addListener(tu chce dodać poniższą funkcje);
    //        // można by jeszcze np. zmieniać wygląd buttona
    //    }
    //    
    //    private void updateButtonText(ObservableValue<?> observable, Object oldVal, Object newVal) {
    //        this.button.setText(initialText + newVal);
    //    }
} 
