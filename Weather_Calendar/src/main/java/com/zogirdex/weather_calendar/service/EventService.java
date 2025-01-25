package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.config.AppConstants;

/**
 *
 * @author tom3k
 */
public class EventService {
    private final EventManager eventManager;
    
    public EventService() {
        this.eventManager = EventManager.getInstance();
    }
    
    public void addEvent(CalendarItem item, String eventName, String eventDesc, String location) {
        this.validateCalendarItem(item);
        this.validateEventName(eventName);
        this.validateEventDesc(eventDesc);
        this.validateLocation(location);
        ScheduledEvent newEvent = new ScheduledEvent(eventName, eventDesc, location);
        this.eventManager.addEvent(item.getDate(), newEvent);
        this.bindEventToCalendarItem(item, newEvent);
    }
        
    public ScheduledEvent getEvent(CalendarItem item) {
        this.validateCalendarItem(item);
        ScheduledEvent event = this.eventManager.getEvent(item.getDate());
        if(event == null) {
            throw new IllegalArgumentException("Nie udało się odnaleźć określonego spotkania.");
        }
        return event;
    }
    
    public void bindEventToCalendarItem(CalendarItem item, ScheduledEvent event) {
        this.setCalendarItemText(item, event.getEventName());
        event.eventNameProperty().addListener((observable, oldVal, newVal) -> {
            this.setCalendarItemText(item, newVal);
        });
    }
    
    private void setCalendarItemText(CalendarItem item, String eventName) {
        item.getButton().setText(String.valueOf(item.getDate().getDayOfMonth())
                    .concat("\n")
                    .concat(eventName));
    }
    
    private void validateEventName(String eventName) {
        if (eventName == null || eventName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa spotkania nie może być pusta.");
        }

        if (eventName.trim().length() < 3) {
            throw new IllegalArgumentException("Nazwa spotkania musi zawierać przynajmniej 3 znaki.");
        }

        if (eventName.length() > 30) {
            throw new IllegalArgumentException("Nazwa spotkania nie może przekraczać 30 znaków.");
        }
    }
    
    private void validateEventDesc(String eventDesc) {
        if (eventDesc == null || eventDesc.length() > 500) {
            throw new IllegalArgumentException("Opis spotkania nie może przekraczać 500 znaków.");
        }
    }
    
    private void validateLocation(String location) {
        if (location == null || !AppConstants.LOCATIONS.contains(location)) {
            throw new IllegalArgumentException("Podana lokalizacja nie jest dostępna.");
        }
    }
    
    private void validateCalendarItem(CalendarItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Przekazano pusty CalendarItem.");
        }
    }
}
