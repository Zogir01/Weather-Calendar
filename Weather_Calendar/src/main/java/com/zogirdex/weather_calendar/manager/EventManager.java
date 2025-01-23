package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.config.AppConstants;
import com.zogirdex.weather_calendar.util.GlobalStateAssistant;
import com.zogirdex.weather_calendar.util.GlobalStateException;
import com.zogirdex.weather_calendar.model.ScheduledEvent;
import com.zogirdex.weather_calendar.util.WeatherApiException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * EventManager przechowuje globalny stan aplikacji odnośnie utworzonych spotkań. Jest to klasa singleton, a więc
 * dostęp do tej klasy jest możliwy z każdej innej klasy aplikacji. Możliwe jest stworzenie tylko pojedyńczej instancji tej klasy
 * (singleton), a więc model danych (kolekcja z eventami) także może mieć tylko jedną instancję.
 * 
 * (W projekcie założono, że z klas typu "Manager" mogą korzystać tylko klasy typu "Service" lub klasa "App".)
 */
public class EventManager {
    /*
    * Instancja klasy singleton EventManager.
    */
    private static EventManager instance;
    
    /*
    *  Model danych aplikacji - kolekcja spotkań jako obiekt ObservableMap.
    */
    private final ObservableMap<LocalDate, ScheduledEvent> events;
   
    
    /*
    * Prywatny konstruktor EventManager, który jest wywoływany tylko raz, w metodzie getInstance() klasy EventManager.
    * Jest to podstawowy zamysł wzorca Singleton. W konstruktorze tym, odczytywany jest stan kolekcji events z pliku binarnego,
    w przypadku niepowodzenia wczytywania stanu, events jest inicjalizowany jako pusty observableHashMap.
    @return instancja EventManager.
    */
    private EventManager() {
        HashMap<LocalDate, ScheduledEvent> state;
        try {
             state = GlobalStateAssistant.loadState(AppConstants.PATH_EVENTS_STATE);
        }
        catch(GlobalStateException ex) {
            state = new HashMap();    
            // LOGUJ: "Error occured while loading global events state. Initializing with" + "an empty collection." + ex.what()
        }
        this.events = javafx.collections.FXCollections.observableMap(state);
    }
    
    // getInstance wzorca singleton (synchronized, aby ułatwić wielowątkowość, którą można by zaimplementować)
    public static synchronized EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public ScheduledEvent getEvent(LocalDate date) {
        return events.getOrDefault(date, null);
    }

    public void addEvent(LocalDate date, ScheduledEvent event) throws WeatherApiException{
        events.put(date, event);
        
        if(AppConstants.WEATHER_API_AUTO_QUERY) {
            try {
                WeatherManager.getInstance().makeQuery(event.getLocation());
            }
            catch(WeatherApiException ex) {
                    throw new WeatherApiException("When new event was added, error occured "
                            + "while performing weather api query.", ex);
            }
        }
    }
    
    public ObservableMap<LocalDate, ScheduledEvent> getEvents() {
        return events;
    }
    
    public boolean eventExists(LocalDate date) {
        return this.events.containsKey(date);
    }
    
    // wymagana jest zmiana z ObservableMap na HashMap, gdyż ObservableMap nie implementuje
    // interfejsu Serializable.
    public final void saveEventsState() throws GlobalStateException {
        GlobalStateAssistant.saveState(new HashMap<>(this.events), AppConstants.PATH_EVENTS_STATE);
    }
}
