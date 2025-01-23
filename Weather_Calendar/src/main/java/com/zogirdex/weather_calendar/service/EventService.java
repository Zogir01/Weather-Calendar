package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.manager.WeatherManager;
import com.zogirdex.weather_calendar.model.WeatherDay;
import com.zogirdex.weather_calendar.uiutil.CalendarButton;
import com.zogirdex.weather_calendar.util.WeatherApiException;
import com.zogirdex.weather_calendar.util.GlobalStateException;


/**
 *
 * @author tom3k
 */
public class EventService {
    private final EventManager eventManager;
    private final WeatherManager weatherManager;
    
    public EventService() throws WeatherApiException{
        this.eventManager = EventManager.getInstance();
        try {
            this.weatherManager = WeatherManager.getInstance();
        }
        catch(WeatherApiException ex) {
            throw new WeatherApiException("Wystąpił błąd komunikacji z api pogodowym.", ex);
        }
    }
    
    public void addEvent(CalendarItem item, String eventName, String eventDesc, String location) throws WeatherApiException {
        CalendarService.validateCalendarItem(item);
        
        if(eventName.length() <= 3) {
            //throw
        }
        ScheduledEvent newEvent = new ScheduledEvent(eventName, eventDesc, location,
                String.valueOf(item.getDate().getDayOfMonth()));
        
        CalendarButton button = item.getButton();
        button.textProperty().bind(newEvent.calendarTextProperty());
        
        try {
            this.eventManager.addEvent(item.getDate(), newEvent);
        }
        catch (WeatherApiException ex) {
            throw new WeatherApiException("Wystąpił błąd podczas pobierania danych pogodowych dla nowo dodanego spotkania.", ex);
        }
        
        // UWAGA UWAGA UWAGA!!!!!
        // średnie rozwiązanie, gdyż jakby WeatherApiAssistant przetwarzał zapytania wielowątkowo, to możliwe, że 
        // WeatherManager nie został jeszcze zaaktualizowany.
        // A więc, można by zrobić listener, że w momencie jak pojawi się WeatherDay o tej dacie, jakaś metoda
        // zaaktualizuje CalendarItem (wyszuka konkretny)
        WeatherDay weatherDay = this.weatherManager.getWeatherDay(item.getDate(), newEvent.getLocation());

        if(weatherDay != null) {
            button.setBackgroundImage("/img/weather-icon/" + weatherDay.getIcon() + ".png");

            weatherDay.iconProperty().addListener( (observable, oldVal, newVal) -> {
                button.setBackgroundImage("/img/weather-icon/" + newVal + ".png");
            });
        }
        
    }
        
    public ScheduledEvent getEvent(CalendarItem item) throws WeatherApiException{
        CalendarService.validateCalendarItem(item);
        ScheduledEvent event = this.eventManager.getEvent(item.getDate());
        
        if(event == null ) {
            event = new ScheduledEvent("brak danych", "brak danych", "brak danych",
                    String.valueOf(item.getDate().getDayOfMonth()));
        }
        return event;
    }
   

}
