package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.uiutil.CalendarButton;
import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.util.WeatherApiException;
import com.zogirdex.weather_calendar.manager.WeatherManager;
import com.zogirdex.weather_calendar.model.WeatherDay;
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
    //List<List<CalendarItem>> Calendars;
    // lub np. map<YearMonth, List<CalendarItem>
    // jakbyśmy chcieli stworzyć listę kalendarzy - czyli zapisywać je w pamięci, żeby ich na nowo nie generować.
    // przyda się też wtedy coś w stylu:     public void update(CalendarItem)
    private final EventManager eventManager;
    private final WeatherManager weatherManager;
    
    public CalendarService() throws WeatherApiException {
        this.eventManager = EventManager.getInstance();
        try {
            this.weatherManager = WeatherManager.getInstance();
        }
        catch(WeatherApiException ex) {
            throw new WeatherApiException("Error while initalizing WeatherManager in CalendarService", ex);
        }
    }
    
    public List<CalendarItem> generateCalendar(Year year, Month month, boolean showDayNumbers, 
            boolean bindToEvent) throws WeatherApiException {
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
                ScheduledEvent event = this.eventManager.getEvent(date);
                
                if(event != null) {
                    button.textProperty().bind(event.calendarTextProperty());
                    WeatherDay weatherDay = this.weatherManager.getWeatherDay(date, event.getLocation());
                    
                    if(weatherDay != null) {
                        button.setBackgroundImage("/img/weather-icon/" + weatherDay.getIcon() + ".png");

                        weatherDay.iconProperty().addListener((observable, oldVal, newVal) -> {
                            button.setBackgroundImage("/img/weather-icon/" + newVal + ".png");
                        });
                    }
                }
                calendarItems.add(item);

                if (col % 7 == 0) {
                    row++;
                }
         }
         return calendarItems;
    }
    
    public static void validateCalendarItem(CalendarItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Przekazano pusty CalendarItem.");
        }
    }
}