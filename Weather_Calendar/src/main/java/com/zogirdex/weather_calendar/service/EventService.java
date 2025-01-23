package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.util.WeatherApiException;

/**
 *
 * @author tom3k
 */
public class EventService {
    private final EventManager eventManager;
    
    public EventService() {
        this.eventManager = EventManager.getInstance();
    }
    
    public void addEvent(CalendarItem item, String eventName, String eventDesc, String location) throws WeatherApiException {
        this.validateCalendarItem(item);
        
        if(eventName.length() <= 3) {
            //throw
        }
        ScheduledEvent newEvent = new ScheduledEvent(eventName, eventDesc, location,
                String.valueOf(item.getDate().getDayOfMonth()));
        try {
            this.eventManager.addEvent(item.getDate(), newEvent);
        }
        catch (WeatherApiException ex) {
            throw new WeatherApiException("Wystąpił błąd podczas pobierania danych pogodowych dla nowo dodanego spotkania.", ex);
        }
    }
        
    public ScheduledEvent getEvent(CalendarItem item) {
        this.validateCalendarItem(item);
        ScheduledEvent event = this.eventManager.getEvent(item.getDate());
        return event != null ? event : new ScheduledEvent("", "", "", String.valueOf(item.getDate().getDayOfMonth()));
    }
    
    public void bindEventToCalendarItem(CalendarItem item) {
        ScheduledEvent event = this.eventManager.getEvent(item.getDate());
        if(event != null) {
            item.getButton().textProperty().bind(event.calendarTextProperty());
        }
    }
    
    private void validateCalendarItem(CalendarItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Przekazano pusty CalendarItem.");
        }
    }
}
