package com.zogirdex.weather_calendar.config;

import java.util.Map;

/**
 *
 * @author tom3k
 * Dobrym pomysłem było zrobić konfiguracje aplikacji w plikach (np. JSON) i robić wczytywanie tego configu.
 * Wadą obecnego podejścia z "AppConstants" jest to, że aby zmienić konfigurację aplikacji, trzeba ją 
 * na nowo skompilować.
 * 
 */
public class AppConstants {

    // App general settings
//    public static final String APP_NAME = "Weather Calendar";
//    public static final String APP_VERSION = "0.1";
    public static final boolean DEBUG_MODE = true;
    
    // Resources settings
    public static final String RESOURCES_PATH = "/com/zogirdex/weather_calendar/";
    
    public static final String CSS_STYLES_PATH = RESOURCES_PATH + "styles.css";
    public static final String CALENDAR_FXML_PATH = RESOURCES_PATH + "calendar.fxml";
    public static final String EVENT_FXML_PATH = RESOURCES_PATH + "event.fxml";
    
    public static final String EVENTS_STATE_PATH = "eventmanager.dat"; // przerobić aby te ścieżki były także w RESOURCES_PATH
    public static final String FILES_STATE_PATH = "filemanager.dat"; // przerobić aby te ścieżki były także w RESOURCES_PATH

    // API settings
    public static final String WEATHER_API_BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    public static final boolean WEATHER_API_AUTO_QUERY = false;
    
    public static final Map<String, String> QUERY_PARAMS = Map.of(
        "unitGroup", "metric",
        "elements", "datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon",
        "include", "days%2Cfcst",
        "key", "VHEMMB29AXXDT86HR399VV4RT",
        "contentType", "json"
    );
    
    //public static final int WEATHER_API_TIMEOUT = 5000; // moze by sie przydalo w przyszlosci
    //String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=metric&elements=datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon&include=days%2Cfcst&key=VHEMMB29AXXDT86HR399VV4RT&contentType=json";
    
    // Logger settings - moze w przyszlosci
//    public static final String LOG_LEVEL = "INFO";
//    public static final String LOG_FILE = "logs/app.log";
}
