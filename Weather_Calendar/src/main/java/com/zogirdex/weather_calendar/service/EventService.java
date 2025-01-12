package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.service.CalendarService;
import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.util.WeatherApiAssistant;
import com.zogirdex.weather_calendar.util.WeatherApiException;

import java.io.IOException;

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
        CalendarService.validateCalendarItem(item);
        
        if(eventName.length() <= 3) {
            //throw
        }
        ScheduledEvent newEvent = new ScheduledEvent(eventName, eventDesc, location,
                String.valueOf(item.getDate().getDayOfMonth()));
        
        item.getButton().textProperty().bind(newEvent.calendarTextProperty());
        this.eventManager.addEvent(item.getDate(), newEvent);
        
        if(AppConstants.WEATHER_API_AUTO_QUERY) {
            try {
                this.eventManager.makeWeatherQuery(item.getDate());
            }
            catch (WeatherApiException ex) {
                throw new WeatherApiException("Wystąpił błąd podczas pobierania danych pogodowych dla nowo dodanego spotkania.", ex);
            }
        }
    }
        
    public ScheduledEvent getEvent(CalendarItem item) throws WeatherApiException{
        CalendarService.validateCalendarItem(item);
        ScheduledEvent event = this.eventManager.getEvent(item.getDate());
        
        if(event == null ) {
            event = new ScheduledEvent("brak danych", "brak danych", "brak danych",
                    String.valueOf(item.getDate().getDayOfMonth()));
        }
        
//        if(event != null) {
//            if(AppConstants.WEATHER_API_AUTO_QUERY) {
//                try {
//                    this.eventManager.makeWeatherQuery(item.getDate());
//                }
//                catch (WeatherApiException ex) {
//                    throw new WeatherApiException("Wystąpił błąd podczas pobierania danych pogodowych dla istniejącego spotkania.", ex);
//                }
//            }
//        }
//        else {
//            event = new ScheduledEvent("brak danych", "brak danych", "brak danych",
//                    String.valueOf(item.getDate().getDayOfMonth()));
//        }
        return event;
    }
   

}
