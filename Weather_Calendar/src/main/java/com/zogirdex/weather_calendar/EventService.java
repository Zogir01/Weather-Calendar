/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zogirdex.weather_calendar;

import java.time.LocalDate;
/**
 *
 * @author tom3k
 */
public class EventService {
    private final EventManager eventManager;
    
    public EventService() {
        this.eventManager = EventManager.getInstance();
    }
    
    public void addEvent(CalendarItem item, String eventName, String eventDesc) {
        this.validateCalendarItem(item);
        
        if(eventName.length() <= 3) {
            //throw
        }
        ScheduledEvent newEvent = new ScheduledEvent(eventName, eventDesc, 
                String.valueOf(item.getDate().getDayOfMonth()));
        
        item.getButton().textProperty().bind(newEvent.calendarTextProperty());
        this.eventManager.addEvent(item.getDate(), newEvent);
    }
    
    public WeatherDay getWeatherDay(CalendarItem item)  {
        this.validateCalendarItem(item);
        
        LocalDate date = item.getDate();
        WeatherDay weatherDay = this.eventManager.getWeatherDay(date);
        
        if(weatherDay == null) {
            // można by logować te informacje
            // np. logger.println("Brak danych pogody dla daty:" + localDate, tworze nowy obiekt.)
            weatherDay = new WeatherDay(
                    date.toString(), 
                    0, 0, 0, 0, 0, 0, 0, 
                    "brak danych", "brak danych" ,"brak danych", "brak danych", "brak danych");
        }
        return weatherDay;
    }
    
    public ScheduledEvent getEvent(CalendarItem item) {
        this.validateCalendarItem(item);
        
        ScheduledEvent event = this.eventManager.getEvent(item.getDate());
        
        if (event == null) {
            event = new ScheduledEvent("brak danych", "brak danych", 
                    String.valueOf(item.getDate().getDayOfMonth()));
        }
        return event;
    }
    
    private void validateCalendarItem(CalendarItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Given calendarItem item was null.");
        }
    }
}
