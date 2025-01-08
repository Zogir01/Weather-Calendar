package com.zogirdex.weather_calendar;

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
