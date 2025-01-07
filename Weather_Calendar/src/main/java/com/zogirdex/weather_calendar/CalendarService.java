package com.zogirdex.weather_calendar;

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
    
    private static CalendarService instance;
    
    public static CalendarService getInstance() {
        if (instance == null) {
            instance = new CalendarService();
        }
        return instance;
    }
    
    private CalendarService() {
        
    }
    
    public List<CalendarItem> generateCalendar(Year year, Month month, boolean showDayNumbers, boolean bindToEvent) {
        List<CalendarItem> calendarItems = new ArrayList<>();
        
        int daysInMonth = month.length(year.isLeap());
        int shift = LocalDate.of(year.getValue(), month, 1).getDayOfWeek().getValue() - 1;
        int row = 1;
        
        for (int col = 1 + shift; col <= daysInMonth + shift; col++) {
            int day = col - shift;
            
            CalendarItem item = new CalendarItem(
                    LocalDate.of(year.getValue(), month, day), 
                    (col - 1) % 7, 
                    row, 
                    showDayNumbers,
                    bindToEvent
            );
            calendarItems.add(item);
            
            if (col % 7 == 0) {
                row++;
            }
        }
        
        return calendarItems;
    }
}