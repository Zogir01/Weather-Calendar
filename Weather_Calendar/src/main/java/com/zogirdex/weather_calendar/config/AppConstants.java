package com.zogirdex.weather_calendar.config;

import java.util.Map;
import java.util.List;
/**
 *
 * @author tom3k
 * Dobrym pomysłem było zrobić konfiguracje aplikacji w plikach (np. JSON) i robić wczytywanie tego configu.
 * Wadą obecnego podejścia z "AppConstants" jest np. to, że aby zmienić konfigurację aplikacji, trzeba ją 
 * na nowo skompilować.
 * 
 */
public class AppConstants {
    //----------------------------------- STAGE --------------------------------------------------------
    public static final String CALENDAR_STAGE_NAME = "Kalendarz";
    public static final int CALENDAR_STAGE_MIN_WIDTH = 640;
    public static final int CALENDAR_STAGE_MIN_HEIGHT = 400;
    
    public static final String ADD_EVENT_STAGE_NAME = "Dodaj spotkanie";
    public static final int ADD_EVENT_STAGE_MIN_WIDTH = 0;
    public static final int ADD_EVENT_STAGE_MIN_HEIGHT = 0;
   
    //----------------------------------- GENERAL ----------------------------------------------------
    public static final String APP_VERSION = "0.1";
    
    //----------------------------------- USER ----------------------------------------------------------
    public static final int YEARS_FORWARD_SCOPE = 4;
    
    //----------------------------------- DEBUG -------------------------------------------------------
    public static final boolean DEBUG_MODE = true;
    
    //----------------------------------- RESOURCE -------------------------------------------------
    public static final String PATH_RESOURCES = "/com/zogirdex/weather_calendar/";
    public static final String PATH_CSS_STYLES = PATH_RESOURCES + "styles.css";
    public static final String PATH_FXML_CALENDAR = PATH_RESOURCES + "calendar.fxml";
    public static final String PATH_FXML_EVENT = PATH_RESOURCES + "event.fxml";
    public static final String PATH_EVENTS_STATE = "eventmanager.dat"; // przerobić aby te ścieżki były także w RESOURCES_PATH
    public static final String PATH_FILES_STATE = "filemanager.dat"; // przerobić aby te ścieżki były także w RESOURCES_PATH
    //public static final 

    //----------------------------------- WEATHER API -----------------------------------------------
    public static final String WEATHER_API_BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    public static final boolean WEATHER_API_AUTO_QUERY = true;
    public static final Map<String, String> WEATHER_API_QUERY_PARAMS = Map.of(
        "unitGroup", "metric",
        "elements", "datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon",
        "include", "days%2Cfcst",
        "key", "VHEMMB29AXXDT86HR399VV4RT",
        "contentType", "json"
    );
    //String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=metric&elements=datetime%2Cname%2Ctemp%2Chumidity%2Cprecip%2Cprecipprob%2Csnow%2Cpressure%2Ccloudcover%2Csunrise%2Csunset%2Cconditions%2Cdescription%2Cicon&include=days%2Cfcst&key=VHEMMB29AXXDT86HR399VV4RT&contentType=json";
}
