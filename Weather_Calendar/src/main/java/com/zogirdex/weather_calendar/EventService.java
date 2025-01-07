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
        CalendarEvent newEvent = new CalendarEvent(eventName, eventDesc);
        this.eventManager.addEvent(item.getDate(), newEvent);
        this.eventManager.bindCalendarItemToEvent(item);
    }
    
    public WeatherDay getWeatherDay(CalendarItem item)  {
        this.validateCalendarItem(item);
        
        LocalDate date = item.getDate();
        WeatherDay weatherDay = this.eventManager.getWeatherDay(date);
        
        if(weatherDay == null) {
            // można by logować te informacje
            // np. logger.println("Brak danych pogody dla daty:" + localDate)
            weatherDay = new WeatherDay(
                    date.toString(), 
                    0, 0, 0, 0, 0, 0, 0, 
                    "brak danych", "brak danych" ,"brak danych", "brak danych", "brak danych");
        }
        return weatherDay;
    }
    
    public CalendarEvent getEvent(CalendarItem item) {
        this.validateCalendarItem(item);
        
        CalendarEvent event = this.eventManager.getEvent(item.getDate());
        
        if (event == null) {
            event = new CalendarEvent("brak danych", "brak danych");
        }
        return event;
    }
    
    private void validateCalendarItem(CalendarItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Given calendarItem item was null.");
        }
    }
}
