package com.zogirdex.weather_calendar.manager;

import com.zogirdex.weather_calendar.model.WeatherDay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.util.Pair;

import java.time.LocalDate;

/**
 *
 * @author tom3k
 * Nowa wersja, aby możliwe było pobierania pogody dla różnych lokalizacji dodano Pair<LocalDate, String>
 */
public class WeatherManager {
    private static WeatherManager instance;
    private final ObservableMap<Pair<LocalDate, String>, WeatherDay> weatherDays = FXCollections.observableHashMap();

    private WeatherManager() {
    }
    
    // getInstance wzorca singleton (synchronized, aby ułatwić wielowątkowość, którą można by zaimplementować)
    public static synchronized WeatherManager getInstance() {
        if (instance == null) {
            instance = new WeatherManager();
        }
        return instance;
    }

    public WeatherDay getWeatherDay(LocalDate date, String location) {
        return this.weatherDays.getOrDefault(new Pair<>(date, location), null);
    }

    public void addWeatherDay(LocalDate date, String location, WeatherDay weatherDay) {
        this.weatherDays.put(new Pair<>(date, location), weatherDay);
    }
}

//package com.zogirdex.weather_calendar.manager;
//
//import com.zogirdex.weather_calendar.model.WeatherDay;
//import java.time.LocalDate;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableMap;
//
///**
// *
// * @author tom3k
// */
//public class WeatherManager {
//    private static WeatherManager instance;
//    private final ObservableMap<LocalDate, WeatherDay> weatherDays = FXCollections.observableHashMap();
//   
//    private WeatherManager() {
//    }
//    
//    // getInstance wzorca singleton (synchronized, aby ułatwić wielowątkowość, którą można by zaimplementować)
//    public static synchronized WeatherManager getInstance() {
//        if (instance == null) {
//            instance = new WeatherManager();
//        }
//        return instance;
//    }
//    
//    public WeatherDay getWeatherDay(LocalDate date) {
//        return this.weatherDays.getOrDefault(date, null);
//    }
//    
//    public void addWeatherDay(LocalDate date, WeatherDay weatherDay) {
//        this.weatherDays.put(date, weatherDay);
//    }
//}
