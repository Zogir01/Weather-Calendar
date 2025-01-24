package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.uiutil.CalendarButton;
import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.util.ApiException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author tom3k
 */
public class CalendarService {
    private final EventService eventService;
    private final WeatherService weatherService;
    
    public CalendarService() throws ApiException {
        this.eventService = new EventService();
        this.weatherService = new WeatherService();
    }
    
    public List<CalendarItem> generateCalendar(Year year, Month month, boolean showDayNumbers, boolean bindToEvent) throws ApiException {
            List<CalendarItem> calendarItems = new ArrayList<>();
            int daysInMonth = month.length(year.isLeap());
            int shift = LocalDate.of(year.getValue(), month, 1).getDayOfWeek().getValue() - 1;
            int row = 1;
            
            for (int col = 1 + shift; col <= daysInMonth + shift; col++) {
                int day = col - shift;
                LocalDate date = LocalDate.of(year.getValue(), month, day);
                String initialText;
                CalendarButton button;
                
                if(showDayNumbers) {
                    initialText = String.valueOf(date.getDayOfMonth()).concat("\n");
                    button = new CalendarButton(String.valueOf(date.getDayOfMonth()), date.toString());
                }
                else {
                    initialText = "";
                    button = new CalendarButton("", date.toString());
                }

                CalendarItem item = new CalendarItem(date, (col - 1) % 7, row, button, initialText);
                if (bindToEvent) {
                    try {
                        ScheduledEvent event = eventService.getEvent(item);
                        eventService.bindEventToCalendarItem(item, event);
                        weatherService.bindWeatherIconToCalendarItem(item, weatherService.getWeatherDay(item, event.getLocation()));     
                    }
                     catch(Exception ex) {
                         
                     }           
                }
                calendarItems.add(item);

                if (col % 7 == 0) {
                    row++;
                }
         }
         return calendarItems;
    }
}