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

    // API settings
    public static final String WEATHER_API_BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    public static final boolean WEATHER_API_AUTO_QUERY = true;
    
    public static final Map<String, String> QUERY_PARAMS = Map.of(
        "unitGroup", "metric",
        "elements", "datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon",
        "include", "days%2Cfcst",
        "key", "VHEMMB29AXXDT86HR399VV4RT",
        "contentType", "json"
    );
    
    //public static final int WEATHER_API_TIMEOUT = 5000; // w milisekundach
    //String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=metric&elements=datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon&include=days%2Cfcst&key=VHEMMB29AXXDT86HR399VV4RT&contentType=json";
    
    // Logger settings
//    public static final String LOG_LEVEL = "INFO";
//    public static final String LOG_FILE = "logs/app.log";
}
