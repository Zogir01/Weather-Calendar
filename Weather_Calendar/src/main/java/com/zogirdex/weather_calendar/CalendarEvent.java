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
    private StringProperty weatherInfo;
    
    public CalendarEvent() {
        this.eventName = new SimpleStringProperty("");
        this.eventDesc = new SimpleStringProperty("");
        this.weatherInfo = new SimpleStringProperty("");
    }
    
    public CalendarEvent(String eventName, String eventDesc, String weatherInfo) {
        this.eventName = new SimpleStringProperty(eventName);
        this.eventDesc = new SimpleStringProperty(eventDesc);
        this.weatherInfo = new SimpleStringProperty(weatherInfo);
    }
    
    public StringProperty eventNameProperty() { return this.eventName; }
    public String getEventName() { return this.eventName.get(); }
    public void setEventName(String eventName) { this.eventName.set(eventName); }
    
    public StringProperty eventDescProperty() { return this.eventDesc; }
    public String getEventDesc() { return this.eventDesc.get(); }
    public void setEventDesc(String eventDesc) { this.eventDesc.set(eventDesc); }
    
    public StringProperty weatherInfoProperty() { return this.weatherInfo; }
    public String getWeatherInfo() { return this.weatherInfo.get(); }
    public void setWeatherInfo(String weatherInfo) { this.weatherInfo.set(weatherInfo); }
}
