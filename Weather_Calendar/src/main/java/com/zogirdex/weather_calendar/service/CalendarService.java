package com.zogirdex.weather_calendar.service;

import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.uiutil.CalendarButton;
import com.zogirdex.weather_calendar.model.CalendarItem;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.util.ApiException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.EnumMap;

public class CalendarService {
    private final EventService eventService;
    private final WeatherService weatherService;
    private List<Year> availableYears;
    private Map<Month, String> availableMonths;
    
    public CalendarService() {
        this.eventService = new EventService();
        this.weatherService = new WeatherService();
        this.availableYears = new ArrayList();
        this.availableMonths = new EnumMap<>(Month.class);
        
        int curYear = this.getCurrentYear().getValue();
        for (int i = 0; i < AppConstants.YEARS_FORWARD_SCOPE; i++) {
           this.availableYears.add(Year.of(curYear + i));
        }
        
        this.availableMonths.put(Month.JANUARY, "Styczeń");
        this.availableMonths.put(Month.FEBRUARY, "Luty");
        this.availableMonths.put(Month.MARCH, "Marzec");
        this.availableMonths.put(Month.APRIL, "Kwiecień");
        this.availableMonths.put(Month.MAY, "Maj");
        this.availableMonths.put(Month.JUNE, "Czerwiec");
        this.availableMonths.put(Month.JULY, "Lipiec");
        this.availableMonths.put(Month.AUGUST, "Sierpień");
        this.availableMonths.put(Month.SEPTEMBER, "Wrzesień");
        this.availableMonths.put(Month.OCTOBER, "Październik");
        this.availableMonths.put(Month.NOVEMBER, "Listopad");
        this.availableMonths.put(Month.DECEMBER, "Grudzień");
    }
    
    public List<CalendarItem> generateCalendar(Year year, String translatedMonthName, boolean showDayNumbers, boolean bindToEvent) throws ApiException {
        Month month = this.getMonthByTranslatedName(translatedMonthName);
        return this.generateCalendar(year, month, showDayNumbers, bindToEvent);
    }
    
    public List<CalendarItem> generateCalendar(Year year, Month month, boolean showDayNumbers, 
            boolean bindToEvent) throws ApiException {
        this.validateYear(year);
        this.validateMonth(month);
    
        List<CalendarItem> calendarItems = new ArrayList<>();
        int daysInMonth = month.length(year.isLeap());
        int shift = LocalDate.of(year.getValue(), month, 1).getDayOfWeek().getValue() - 1;
        int row = 1;

        for (int col = 1 + shift; col <= daysInMonth + shift; col++) {
            int day = col - shift;
            LocalDate date = LocalDate.of(year.getValue(), month, day);
            CalendarItem item = createCalendarItem(date, col, row, showDayNumbers);
            if (bindToEvent) {
                this.bindEventAndWeatherToCalendarItem(item);
            }
            calendarItems.add(item);
            if (col % 7 == 0) {
                row++;
            }
         }
         return calendarItems;
    }
    
    public List<Year> getAvailableYears() {
        return this.availableYears;
    }
    
    public Map<Month, String> getAvailableMonths() {
        return this.availableMonths;
    }
    
    public List<String> getAvailableMonthsTranslated() {
        return new ArrayList<>(this.availableMonths.values());
    }
    
    public Month getMonthByTranslatedName(String name) {
        return this.availableMonths.entrySet()
            .stream()
            .filter(entry -> entry.getValue().equals(name)) 
            .map(Map.Entry::getKey) 
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono miesiąca dla nazwy: " + name));
    }
    
    public final Year getCurrentYear() {
        return Year.now();
    }
    
    public final Month getCurrentMonth() {
        return LocalDate.now().getMonth();
    }
    
    public String getCurrentMonthTranslated() {
        return this.availableMonths.get(this.getCurrentMonth());
    }
    
    private void bindEventAndWeatherToCalendarItem(CalendarItem item) {
        try {
            ScheduledEvent event = this.eventService.getEvent(item);
            if (event != null) {
                this.eventService.bindEventToCalendarItem(item, event);
                this.weatherService.bindWeatherIconToCalendarItem(item, event.getLocation());
            }
        } catch (Exception ex) {}
    }
    
    private CalendarItem createCalendarItem(LocalDate date, int col, int row, boolean showDayNumbers) {
        String initialText;
        CalendarButton button;

        if (showDayNumbers) {
            initialText = String.valueOf(date.getDayOfMonth()).concat("\n");
            button = new CalendarButton(String.valueOf(date.getDayOfMonth()), date.toString());
        } else {
            initialText = "";
            button = new CalendarButton("", date.toString());
        }

        return new CalendarItem(date, (col - 1) % 7, row, button, initialText);
    }
    
    private void validateYear(Year year) {
        if (year == null || !this.availableYears.contains(year)) {
            throw new IllegalArgumentException("Brak dostępnego roku kalendarza.");
        }
    }
    
    private void validateMonth(Month month) {
        if (month == null || !this.availableMonths.containsKey(month)) {
            throw new IllegalArgumentException("Brak dostępnego miesiąca kalendarza.");
        }
    }
}