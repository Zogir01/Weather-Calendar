package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.uiutil.CalendarButton;
import com.zogirdex.weather_calendar.uiutil.CalendarItem;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.manager.EventManager;
import com.zogirdex.weather_calendar.util.WeatherApiAssistant;
import com.zogirdex.weather_calendar.util.WeatherApiException;
import com.zogirdex.weather_calendar.config.AppConstants;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;


/**
 *
 * @author tom3k
 */
public class CalendarService {
    //List<List<CalendarItem>> Calendars;
    // lub np. map<YearMonth, List<CalendarItem>
    // jakbyśmy chcieli stworzyć listę kalendarzy - czyli zapisywać je w pamięci, żeby ich na nowo nie generować.
    // przyda się też wtedy coś w stylu:     public void update(CalendarItem)
    private EventManager eventManager;
    
    public CalendarService() {
        this.eventManager = EventManager.getInstance();
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
                    
//                    if(AppConstants.WEATHER_API_AUTO_QUERY) {
//                        // aktualizacja danych pogodowych w przypadku znalezienia eventu.
//                        try {
//                            eventManager.makeWeatherQuery(date);
//                        }
//                        catch(WeatherApiException ex) {
//                            throw new WeatherApiException("Wystąpił błąd podczas pobierania danych pogodowych podczas generowania kalendarza.", ex);
//                        }
//                    }
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