package com.zogirdex.weather_calendar;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tom3k
 */
public class ScheduledEvent {
    private StringProperty eventName;
    private StringProperty eventDesc;
    private StringProperty dayOfMonth;
    
    public ScheduledEvent() {
        this.eventName = new SimpleStringProperty("");
        this.eventDesc = new SimpleStringProperty("");
        this.dayOfMonth = new SimpleStringProperty("");
    }
    
    public ScheduledEvent(String eventName, String eventDesc, String dayOfMonth) {
        this.eventName = new SimpleStringProperty(eventName);
        this.eventDesc = new SimpleStringProperty(eventDesc);
        this.dayOfMonth = new SimpleStringProperty(dayOfMonth);
    }
    
    public StringProperty eventNameProperty() { return this.eventName; }
    public String getEventName() { return this.eventName.get(); }
    public void setEventName(String eventName) { this.eventName.set(eventName); }
    
    public StringProperty eventDescProperty() { return this.eventDesc; }
    public String getEventDesc() { return this.eventDesc.get(); }
    public void setEventDesc(String eventDesc) { this.eventDesc.set(eventDesc); }
    
    public StringProperty dayOfMonthProperty() { return this.dayOfMonth; }
    public String getDayOfMonth() { return this.dayOfMonth.get(); }
    public void setDayOfMonth(String dayOfMonth) { this.dayOfMonth.set(dayOfMonth); }
    
    public StringProperty calendarTextProperty() {
        StringProperty fullText = new SimpleStringProperty();
        fullText.bind(dayOfMonth.concat("\n").concat(eventName));
        return fullText;
    }
}
