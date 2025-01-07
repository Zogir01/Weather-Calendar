package com.zogirdex.weather_calendar;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.ArrayList;
import java.lang.NullPointerException;

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
    
    public List<CalendarItem> generateCalendar(Year year, Month month, boolean showDayNumbers, boolean bindToEvent) {
            List<CalendarItem> calendarItems = new ArrayList<>();

            int daysInMonth = month.length(year.isLeap());
            int shift = LocalDate.of(year.getValue(), month, 1).getDayOfWeek().getValue() - 1;
            int row = 1;

            for (int col = 1 + shift; col <= daysInMonth + shift; col++) {
                int day = col - shift;
                LocalDate date = LocalDate.of(year.getValue(), month, day);
                String initialText;
                DayButton button;

                if(showDayNumbers) {
                    initialText = String.valueOf(date.getDayOfMonth()).concat("\n");
                    button = new DayButton(String.valueOf(date.getDayOfMonth()), date.toString());
                }
                else {
                    initialText = "";
                    button = new DayButton("", date.toString());
                }

                CalendarItem item = new CalendarItem(date, (col - 1) % 7, row, button, initialText);
                
                if(this.eventManager.getEvent(date) != null) {
                    try {
                       if(bindToEvent) {
                            this.eventManager.bindCalendarItemToEvent(item);
                       }
                    }
                    catch(NullPointerException ex) {}
                  }
                
              
                
                calendarItems.add(item);

                if (col % 7 == 0) {
                    row++;
                }
         }
         return calendarItems;
    }
}