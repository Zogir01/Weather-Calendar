package com.zogirdex.weather_calendar;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tom3k
 */
public class CalendarEvent {
    private StringProperty eventName;
    private StringProperty eventDesc;
    private Day day;
    
    public CalendarEvent() {
        this.eventName = new SimpleStringProperty("");
        this.eventDesc = new SimpleStringProperty("");
        this.day = null;
    }
    
    public CalendarEvent(String eventName, String eventDesc, String weatherInfo) {
        this.eventName = new SimpleStringProperty(eventName);
        this.eventDesc = new SimpleStringProperty(eventDesc);
        this.day = null;
    }
    
    public StringProperty eventNameProperty() { return this.eventName; }
    public String getEventName() { return this.eventName.get(); }
    public void setEventName(String eventName) { this.eventName.set(eventName); }
    
    public StringProperty eventDescProperty() { return this.eventDesc; }
    public String getEventDesc() { return this.eventDesc.get(); }
    public void setEventDesc(String eventDesc) { this.eventDesc.set(eventDesc); }
    
    public void setDay(Day day) { this.day = day; }
    public Day getDay() { return this.day; }
    
    
}
